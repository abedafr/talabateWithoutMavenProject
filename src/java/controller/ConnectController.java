/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.User;
import controller.util.HashageUtil;
import controller.util.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import service.UserFacade;

/**
 *
 * @author hp
 */
@Named(value = "connectController")
@SessionScoped
public class ConnectController implements Serializable {

    @EJB
    private UserFacade ejbFacade;
    private User selected;
    private User connected;

    public String createAccount() {
        selected.setPassword(HashageUtil.sha256(selected.getPassword()));
        ejbFacade.create(selected);
        selected.setPassword(null);
        selected = null;
        return "/connexion/SeConnecter";

    }

    public UserFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(UserFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public User getSelected() {
        if (selected == null) {
            selected = new User();
        }
        return selected;
    }

    public void setSelected(User selected) {
        this.selected = selected;
    }

    public String SeConnecter() {
        int res = ejbFacade.seConnnecter(connected);
        if (res == -5) {
            JsfUtil.addErrorMessage("Veuilliez saisir votre login");
            return null;
        } else if (res == -4) {
            JsfUtil.addErrorMessage("Login non Trouver..");
            return null;
        } else if (res == -3) {
            JsfUtil.addErrorMessage("Mot de passe incorrect");
            return null;
        } else if (res == -2) {
            JsfUtil.addErrorMessage("Cet utilisateur est bloqué");
            return null;

        }
        return "/home/Home.xhtml";

    }

    public User getConnected() {
        if(connected == null){
            connected= new User();
        }
        return connected;
    }

    public void setConnected(User connected) {
        this.connected = connected;
    }

    
    /**
     * Creates a new instance of ConnectController
     */
    public ConnectController() {
    }

}
