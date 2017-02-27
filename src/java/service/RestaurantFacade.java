/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Cuisine;
import bean.Menu;
import bean.Quartier;
import bean.Restaurant;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.SearchUtil;

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

    public RestaurantFacade() {
        super(Restaurant.class);
    }


    public List<Restaurant> mainSearch(Quartier quartier, Cuisine cuisine) {
        String req = "SELECT r FROM Restaurant r WHERE 1=1";
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

    
    
}
