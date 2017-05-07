/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Restaurant;
import bean.User;
import controller.util.JsfUtil;
import controller.util.SessionUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import service.RestaurantFacade;
import service.UserFacade;

/**
 *
 * @author hp
 */
@Named(value = "adminController")
@SessionScoped
public class AdminController implements Serializable {

    @EJB
    private service.RestaurantFacade ejbFacade;
    @EJB
    private service.UserFacade userFacade;
    private List<Restaurant> items = null;
    private Restaurant selected;
    private User user;
    
    private String oldPw;
    private String newPw;
    private String confirmPw;

    public void affichage() {
        User loadeUser = userFacade.find(SessionUtil.getConnectedUser().getEmail());
        user = loadeUser;
    }

    public void modifier() throws IOException {
        userFacade.edit(SessionUtil.getConnectedUser());
        user= userFacade.find(SessionUtil.getConnectedUser().getEmail());
        FacesContext.getCurrentInstance().getExternalContext().redirect("../admin/AdminHome.xhtml");
    }

    public void save() {
        userFacade.edit(user);
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
            FacesContext.getCurrentInstance().getExternalContext().redirect("../admin/EditProfil.xhtml");
        }

    }
    
    public Long count(){
        return ejbFacade.countDemande();
    }
    
    public void Delete(Restaurant restaurant) {
        User u = restaurant.getAdminRestau();
        u.setRestaurant(null);
        userFacade.edit(u);
        userFacade.remove(u);
        ejbFacade.remove(restaurant);
        items=null;
        user=null;
        selected=null;
    }

    public void Accept(Restaurant restaurant) throws MessagingException {
        ejbFacade.acceptRestau(restaurant);
        items.remove(items.indexOf(restaurant));
    }
    public RestaurantFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(RestaurantFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<Restaurant> getItems() {
        items = ejbFacade.findBlockedRestaurant();
        return items;
    }

    public void setItems(List<Restaurant> items) {
        this.items = items;
    }

    public Restaurant getSelected() {
        return selected;
    }

    public void setSelected(Restaurant selected) {
        this.selected = selected;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public User getUser() {
        user= userFacade.find(SessionUtil.getConnectedUser().getEmail());
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    /**
     * Creates a new instance of AdminManageController
     */
    public AdminController() {
    }

}
