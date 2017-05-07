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
import java.util.ArrayList;
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
        List<Long> idCuisines = em.createNativeQuery("SELECT mc.cuisines_id FROM menu_cuisine mc WHERE menu_id=" + menu.getId()).getResultList();
        List<Long> idMenus = em.createNativeQuery("SELECT mc.menu_id FROM menu_cuisine mc WHERE cuisines_id=" + cuisine.getId()).getResultList();
        List<Cuisine> cuisines = new ArrayList<>();
        List<Menu> menus = new ArrayList<>();
        for (Long menuId : idMenus) {
            menus.add(menuFacade.find(menuId));
        }
        for (Long id : idCuisines) {
            cuisines.add(cuisineFacade.find(id));
        }

        if (!menus.contains(menu)) {
            menus.add(menu);
        }
        if (!cuisines.contains(cuisine)) {
            cuisines.add(cuisine);
        }
        menu.setCuisines(cuisines);
        cuisine.setMenus(menus);
        cuisineFacade.edit(cuisine);
        menuFacade.edit(menu);
    }

    public List<Plate> getPlateByCuisine(Cuisine cuisine) {
        return em.createQuery("SELECT p FROM Plate p WHERE p.cuisine.id=" + cuisine.getId()).getResultList();
    }

    public List<PlatMenu> findCostumePlatMenus() {
        return em.createQuery("SELECT p FROM PlatMenu p WHERE p.costume=TRUE").getResultList();
    }

    public List<PlatMenu> findRestauPlatMenus(Menu menu) {
        return em.createQuery("SELECT p FROM PlatMenu p WHERE p.menu.id = " + menu.getId()).getResultList();
    }
}
