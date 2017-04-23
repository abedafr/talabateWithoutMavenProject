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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.mail.MessagingException;
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
    private String email;

    public String SendPW() throws MessagingException {
        int res = ejbFacade.sendPW(email);
        if (res < 0) {
            JsfUtil.addErrorMessage("Email non trouvé!\n si vou n'avez pas de compte crée le!");
            return null;
        } else {
            return "/home/Home";
        }
    }

    public String deconnecter() {
        ejbFacade.seDeConnnecter();
        return "/home/Home";

    }

    public String createAccount() {
        selected.setPassword(HashageUtil.sha256(selected.getPassword()));
        ejbFacade.create(selected);
        selected.setPassword(null);
        selected = null;
        return "/connexion/SeConnecter";

    }

//    public void validateSamePassword(FacesContext context, UIComponent toValidate, Object value) {
//        String confirmPassword = (String) value;
//        if (!confirmPassword.equals(selected.getPassword())) {
//            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords do not match!", "Passwords do not match!");
//            throw new ValidatorException(message);
//        }
//    }
    public String SeConnecter() {
        int res = ejbFacade.seConnnecter(connected);
        if (res == -5) {
            JsfUtil.addErrorMessage("Veuilliez saisir votre login");
            return null;
        } else if (res == -4) {
//            JsfUtil.addErrorMessage("Login non Trouver..");
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getConnected() {
        if (connected == null) {
            connected = new User();
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
