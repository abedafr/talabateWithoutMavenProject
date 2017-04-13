/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Cuisine;
import bean.Menu;
import bean.PlatMenu;
import bean.Plate;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abed
 */
@Stateless
public class PlatMenuFacade extends AbstractFacade<PlatMenu> {

    @PersistenceContext(unitName = "TalabatWithoutMavenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlatMenuFacade() {
        super(PlatMenu.class);
    }
    
    @EJB
    private CuisineFacade cuisineFacade;
    @EJB
    private MenuFacade menuFacade;
    
    public void addPlateMenuCuisine(Menu menu, Cuisine cuisine) {
        System.out.println(cuisine);
        System.out.println(menu);
        System.out.println(menu.getCuisines());
        System.out.println(cuisine.getMenus());
        if (!cuisine.getMenus().contains(menu)) {
            cuisine.getMenus().add(menu);
        }
        if (!menu.getCuisines().contains(cuisine)) {
            menu.getCuisines().add(cuisine);
        }
        cuisineFacade.edit(cuisine);
        menuFacade.edit(menu);
    }

    public List<Plate> getPlateByCuisine(Cuisine cuisine) {
        return em.createQuery("SELECT p FROM Plate p WHERE p.cuisine.id="+cuisine.getId()).getResultList();
    }

    public List<PlatMenu> findCostumePlatMenus(){
        return em.createQuery("SELECT p FROM PlatMenu p WHERE p.costume=TRUE").getResultList();
    }
}
