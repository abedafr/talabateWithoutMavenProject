/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Cuisine;
import bean.Quartier;
import bean.Restaurant;
import java.util.ArrayList;
import java.util.List;
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

    public RestaurantFacade() {
        super(Restaurant.class);
    }

    public List<Restaurant> findByCuisine(Long id) {
      //  if (cuisine != null) {
            return em.createQuery("SELECT r FROM Restaurant r WHERE r.menu.cuisines.id=" + id).getResultList();
//        }
//        return null;
    }

    public List<Restaurant> mainSearch(Quartier quartier, Cuisine cuisine) {
        String req = "SELECT r FROM Restaurant r WHERE 1=1";
        req += SearchUtil.addConstraint("r", "quartier.id", "=", quartier.getId());
        if (cuisine != null) {
            List<Restaurant> resultByQrt = em.createQuery(req).getResultList();
            List<Restaurant> resultRestaurants = new ArrayList<>();
            List<Cuisine> cuisines;

            for (Restaurant restaurant : resultByQrt) {
                cuisines = restaurant.getMenu().getCuisines();
                for (Cuisine cuisineRech : cuisines) {
                    if (cuisineRech.equals(cuisine)) {
                        resultRestaurants.add(restaurant);
                    }
                }
            }
            System.out.println(resultRestaurants);
            return resultRestaurants;
        } else {
            System.out.println(em.createQuery(req).getResultList());
            return em.createQuery(req).getResultList();
        }
    }
}
