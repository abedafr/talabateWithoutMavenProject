/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Adress;
import bean.Commande;
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
import service.AdressFacade;
import service.CommandeFacade;
import service.RestaurantFacade;
import service.UserFacade;

@Named(value = "profilcontroller")
@SessionScoped
public class ProfilController implements Serializable {

    private User selected = null;
    @EJB
    private UserFacade userFacade;
    @EJB
    private CommandeFacade commandeFacade;
    @EJB
    private RestaurantFacade restaurantFacade;
    @EJB
    private AdressFacade adressFacade;

    private List<Commande> commandes;
    private List<Restaurant> restaurants;
    private List<Adress> adresses;

    private String oldPw;
    private String newPw;
    private String confirmPw;

    public String affichage() {
        User loadeUser = userFacade.find(SessionUtil.getConnectedUser().getEmail());
        selected = loadeUser;
        return "/profil/MyProfil";
    }

    public String modifier() {
        User loadeUser = userFacade.find(SessionUtil.getConnectedUser().getEmail());
        selected = loadeUser;
        return "/profil/EditProfil";
    }

    public void save() {
        userFacade.edit(selected);
        selected = null;
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

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
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

    public User getSelected() {
        selected = userFacade.find(SessionUtil.getConnectedUser().getEmail());
        return selected;
    }

    public void setSelected(User selected) {
        this.selected = selected;
    }

    public CommandeFacade getCommandeFacade() {
        return commandeFacade;
    }

    public void setCommandeFacade(CommandeFacade commandeFacade) {
        this.commandeFacade = commandeFacade;
    }

    public RestaurantFacade getRestaurantFacade() {
        return restaurantFacade;
    }

    public void setRestaurantFacade(RestaurantFacade restaurantFacade) {
        this.restaurantFacade = restaurantFacade;
    }

    public AdressFacade getAdressFacade() {
        return adressFacade;
    }

    public void setAdressFacade(AdressFacade adressFacade) {
        this.adressFacade = adressFacade;
    }

    public List<Commande> getCommandes() {
        commandes = commandeFacade.findCommandByUser(getSelected());
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public List<Restaurant> getRestaurants() {
        restaurants = restaurantFacade.findRestauByUser(getSelected());
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public List<Adress> getAdresses() {
        adresses = adressFacade.findAdressByUser(getSelected());
        return adresses;
    }

    public void setAdresses(List<Adress> adresses) {
        this.adresses = adresses;
    }

    public ProfilController() {
    }

}
