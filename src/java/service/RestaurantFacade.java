/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commande;
import bean.Cuisine;
import bean.Menu;
import bean.Quartier;
import bean.Restaurant;
import bean.User;
import controller.util.EmailUtil;
import controller.util.HashageUtil;
import controller.util.RandomStringUtil;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import controller.util.SearchUtil;
import javax.mail.MessagingException;

/**
 *
 * @author Abed
 */
@Stateless
public class RestaurantFacade extends AbstractFacade<Restaurant> {

    @PersistenceContext(unitName = "TalabatWithoutMavenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @EJB
    private MenuFacade menuFacade;
    @EJB
    private UserFacade userFacade;

    public RestaurantFacade() {
        super(Restaurant.class);
    }

    public List<Restaurant> mainSearch(Quartier quartier, Cuisine cuisine) {
        String req = "SELECT r FROM Restaurant r WHERE 1=1";
        req += SearchUtil.addConstraint("r", "accepted", "=", true);
        req += SearchUtil.addConstraint("r", "quartier.id", "=", quartier.getId());
        if (cuisine != null) {
            List<Long> resMenus = em.createNativeQuery("SELECT mc.menu_id FROM menu_cuisine mc WHERE cuisines_id=" + cuisine.getId()).getResultList();
            System.out.println("List MenuIds by req = " + resMenus);
            List<Restaurant> resultRestaurants = new ArrayList<>();
            List<Menu> menus = new ArrayList<>();

            for (Long menuId : resMenus) {
                menus.add(menuFacade.find(menuId));
            }
            System.out.println(" ResultMenu Facade = " + menus);
            for (Menu menu : menus) {
                resultRestaurants.add(menu.getRestaurant());
            }
            System.out.println(" ResultRestau Facade = " + resultRestaurants);
            return resultRestaurants;
        } else {
            System.out.println(em.createQuery(req).getResultList());
            return em.createQuery(req).getResultList();
        }
    }

    public List<Restaurant> search(String nom) {

        String req = "select r from Restaurant r where 1=1";
        if (!nom.equals("")) {
            req += " AND r.nom LIKE '%" + nom + "%'";
        }
        return em.createQuery(req).getResultList();

    }

    public List<Restaurant> findAcceptedRestaurant() {
        return em.createQuery("SELECT r FROM Restaurant r WHERE r.accepted='" + true + "'").getResultList();
    }
    public List<Restaurant> findBlockedRestaurant() {
        return em.createQuery("SELECT r FROM Restaurant r WHERE r.accepted='" + false + "'").getResultList();
    }
    public Long countDemande() {
        return (Long) em.createQuery("SELECT count(r.id) FROM Restaurant r WHERE r.accepted='" + false + "'").getSingleResult();
    }

    public void acceptRestau(Restaurant restaurant) throws MessagingException {
        restaurant.setAccepted(true);
        User user = restaurant.getAdminRestau();
        user.setBlocked(false);
        String pw = RandomStringUtil.generate();
        String msg = "Bienvenu Mr. " + user.getNom() + ",<br/>"
                + "D'après votre demande d'ajouter votre restaurant a FOODelivery, nous avons generer ce mot de passe pour vous.\n"
                + "<br/><br/>"
                + "      Nouveau Mot de Passe: <br/><center><b>"
                + pw
                + "</b></center><br/><br/><b><i>PS:</i></b> ce mot de passe vous donne l'acces a votre compte une seule fois,"
                + " une fois que vous avez connecter il faut cree votre propre mot de passe";
        user.setPassword(HashageUtil.sha256(pw));
        edit(restaurant);
        userFacade.edit(user);
        EmailUtil.sendMail("foodelivery.info@gmail.com", "foodelivery", msg, user.getEmail(), "Acceptation");

    }
    
    public List<Restaurant> findRestauByUser(User user){
        if(user!=null){
            return em.createQuery("SELECT DISTINCT c.restaurant  FROM Commande c WHERE c.user.email ='"+user.getEmail()+"'").getResultList();
        }
        return null;
    }
}
