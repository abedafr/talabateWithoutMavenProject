/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Cuisine;
import bean.Menu;
import bean.Plate;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abed
 */
@Stateless
public class PlateFacade extends AbstractFacade<Plate> {

    @PersistenceContext(unitName = "TalabatWithoutMavenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @EJB
    private CuisineFacade cuisineFacade;
    @EJB
    private MenuFacade menuFacade;

    public PlateFacade() {
        super(Plate.class);
    }

    public Plate addPlateMenuCuisine(Plate plate) {
        Cuisine cuisine = plate.getCuisine();
        Menu menu = plate.getMenu();
        if (!cuisine.getMenus().contains(menu)) {
            cuisine.getMenus().add(menu);
        }
        if (!menu.getCuisines().contains(cuisine)) {
            menu.getCuisines().add(cuisine);
        }
        cuisineFacade.edit(cuisine);
        menuFacade.edit(menu);
        return plate;

    }
}
