/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Commande;
import bean.Menu;
import bean.PlatMenu;
import bean.Plate;
import bean.Restaurant;
import bean.Supplement;
import bean.SupplementPlat;
import bean.User;
import controller.util.JsfUtil;
import controller.util.SessionUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import service.CommandeFacade;
import service.MenuFacade;
import service.PlatMenuFacade;
import service.RestaurantFacade;
import service.SupplementPlatFacade;
import service.UserFacade;

/**
 *
 * @author hp
 */
@Named(value = "restauAdminController")
@SessionScoped
public class RestauAdminController implements Serializable {

    @EJB
    private service.RestaurantFacade ejbFacade;
    @EJB
    private service.UserFacade userFacade;
    private User user;
    private Restaurant restaurant;
    private List<Commande> commandes;
    private Menu menu;
    private PlatMenu platMenu;
    @EJB
    private MenuFacade menuFacade;
    private Restaurant selectRestaurant;
    @EJB
    private PlatMenuFacade platMenuFacade;
    @EJB
    private CommandeFacade commandeFacade;
    @EJB
    private SupplementPlatFacade supplementPlatFacade;
    private List<PlatMenu> platMenus;
    private List<SupplementPlat> supplementPlats;
    private List<Supplement> supplements;

    private String oldPw;
    private String newPw;
    private String confirmPw;

    public String affichage() {
        User loadeUser = userFacade.find(SessionUtil.getConnectedUser().getEmail());
        user = loadeUser;
        return "/profil/MyProfil";
    }

    public String modifier() {
        User loadeUser = userFacade.find(SessionUtil.getConnectedUser().getEmail());
        user = loadeUser;
        return "/profil/EditProfil";
    }

    public void save() {
        userFacade.edit(SessionUtil.getConnectedUser());
        user = userFacade.find(SessionUtil.getConnectedUser().getEmail());
    }

    public void editPassword() throws IOException {
        int res = userFacade.changePassword(SessionUtil.getConnectedUser().getEmail(), oldPw, newPw, confirmPw);

        if (res == -1) {
            JsfUtil.addErrorMessage("Nouveau mot de passe incorrect");
        } else if (res == -2) {
            JsfUtil.addErrorMessage("Ancien mot de passe incorrect");
        } else if (res == -3) {
            JsfUtil.addErrorMessage("Vous n'avez rien changé");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../profil/EditProfil.xhtml");
        }

    }

    public void edit() {
        platMenu.setMenu(getMenu());
        platMenuFacade.edit(platMenu);
        platMenus = null;
        platMenu = null;

    }

    public void create() {
        platMenu.setMenu(getMenu());
        platMenuFacade.create(platMenu);
        platMenus = null;
        platMenu = null;

    }

    public List<Plate> plateByCuisine() {
        if (platMenu != null && platMenu.getCuisine() != null) {
            return platMenuFacade.getPlateByCuisine(platMenu.getCuisine());
        } else {
            return new ArrayList<>();
        }
    }
    
    public void saveSupplements() {
        System.out.println("Sups +" + supplements);
        if (supplements != null) {
            List<SupplementPlat> list = new ArrayList();
            for (Supplement item : supplements) {
                SupplementPlat supplementPlat = new SupplementPlat(0D, platMenu, item);
                list.add(supplementPlat);
            }
            System.out.println("SupPlats " + list);
            platMenu.setSupplementPlats(list);
        }
    }

    public Long countCmd(){
        return commandeFacade.countCommandByRestau(getRestaurant());
    }
    public void prepareCreate() {
        platMenu = new PlatMenu();
    }

    public void prepareEdit(PlatMenu item) {
        platMenu = item;
    }

    public void Delete(PlatMenu platMenu) {
        platMenuFacade.remove(platMenu);
        platMenus=null;
    }
    
    public void prepareSup(PlatMenu plat) {
        platMenu = plat;
        supplementPlats = supplementPlatFacade.findByPlatMenu(platMenu);
    }
    public void prepareSupSource(PlatMenu plat) {
        platMenu = plat;
        supplementPlats = supplementPlatFacade.findByPlatMenu(platMenu);
    }

    public PlatMenuFacade getPlatMenuFacade() {
        return platMenuFacade;
    }

    public void setPlatMenuFacade(PlatMenuFacade platMenuFacade) {
        this.platMenuFacade = platMenuFacade;
    }

    public List<PlatMenu> getPlatMenus() {
        Menu myMenu = getMenu();
        platMenus = platMenuFacade.findRestauPlatMenus(myMenu);
        return platMenus;
    }


    public void setPlatMenus(List<PlatMenu> platMenus) {
        this.platMenus = platMenus;
    }

    public Menu getMenu() {
        menu = getSelectRestaurant().getMenu();
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Restaurant getSelectRestaurant() {
        selectRestaurant = getUser().getRestaurant();
        return selectRestaurant;
    }

    public void setSelectRestaurant(Restaurant selectRestaurant) {
        this.selectRestaurant = selectRestaurant;
    }

    public MenuFacade getMenuFacade() {
        return menuFacade;
    }

    public void setMenuFacade(MenuFacade menuFacade) {
        this.menuFacade = menuFacade;
    }

    public List<Commande> getCommandes() {
        commandes= commandeFacade.findCommandByRestau(getRestaurant());
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public SupplementPlatFacade getSupplementPlatFacade() {
        return supplementPlatFacade;
    }

    public void setSupplementPlatFacade(SupplementPlatFacade supplementPlatFacade) {
        this.supplementPlatFacade = supplementPlatFacade;
    }

    public String getOldPw() {
        return oldPw;
    }

    public void setOldPw(String oldPw) {
        this.oldPw = oldPw;
    }

    public String getNewPw() {
        return newPw;
    }

    public void setNewPw(String newPw) {
        this.newPw = newPw;
    }

    public String getConfirmPw() {
        return confirmPw;
    }

    public void setConfirmPw(String confirmPw) {
        this.confirmPw = confirmPw;
    }

    public Restaurant getRestaurant() {

        restaurant = getUser().getRestaurant();

        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public RestaurantFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(RestaurantFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public User getUser() {
        user = userFacade.find(SessionUtil.getConnectedUser().getEmail());
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PlatMenu getPlatMenu() {
        return platMenu;
    }

    public void setPlatMenu(PlatMenu platMenu) {
        this.platMenu = platMenu;
    }

    public List<SupplementPlat> getSupplementPlats() {
        if (supplementPlats == null) {
            supplementPlats = new ArrayList();
        }
        return supplementPlats;
    }

    public void setSupplementPlats(List<SupplementPlat> supplementPlats) {
        this.supplementPlats = supplementPlats;
    }

    public List<Supplement> getSupplements() {
        return supplements;
    }

    public void setSupplements(List<Supplement> supplements) {
        this.supplements = supplements;
    }
    

    /**
     * Creates a new instance of RestauAdminController
     */
    public RestauAdminController() {
    }

}
