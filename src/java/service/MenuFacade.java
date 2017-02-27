/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Cuisine;
import bean.Menu;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abed
 */
@Stateless
public class MenuFacade extends AbstractFacade<Menu> {

    @PersistenceContext(unitName = "TalabatWithoutMavenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }

    public Menu findByCuisine(Menu menu, Cuisine cuisine) {
        List<Cuisine> cuisines = menu.getCuisines();
        for (Cuisine cuisineMenu : cuisines) {
            if (cuisineMenu.equals(cuisine)) {
                return menu;
            }
        }
        return null;
    }
}
