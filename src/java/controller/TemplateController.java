/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.User;
import controller.util.SessionUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import service.UserFacade;

/**
 *
 * @author Abed
 */
@Named(value = "templateController")
@SessionScoped
public class TemplateController implements Serializable {

    private User user = null;
    private @EJB
    UserFacade userFacade;

    /**
     * Creates a new instance of TemplateController
     */
    public TemplateController() {
    }

    public void clientPrivileges() throws IOException {
        if (!UserConnected()) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../private/NotFound.xhtml");
        }
    }

    public void userPrivileges() throws IOException {
        if (!UserConnected()) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../private/NotFound.xhtml");
        } else {
            user = userFacade.find(SessionUtil.getConnectedUser().getEmail());
            if (user.isAdmin() != 0) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../private/AccessDenied.xhtml");
            }
        
    }

}

public void restAdminPrivileges() throws IOException {
        if (!UserConnected()) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../private/NotFound.xhtml");
        } else {
            user = userFacade.find(SessionUtil.getConnectedUser().getEmail());
            if (user.isAdmin() != 1) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../private/AccessDenied.xhtml");

            }
        }
    }

    public void adminPrivileges() throws IOException {
        if (!UserConnected()) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../private/NotFound.xhtml");
        } else {
            user = userFacade.find(SessionUtil.getConnectedUser().getEmail());
            if (user.isAdmin() != 2) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../private/AccessDenied.xhtml");

            } else {

            }
        }
    }

    public void redirect() throws IOException {
        if (UserConnected()) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../home/Home.xhtml");
        }
    }

    public void redirectCmd() throws IOException {
        if (UserConnected()) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../checkout/ConfirmCommand.xhtml");
        }
    }

    public boolean UserConnected() {
        user = SessionUtil.getConnectedUser();
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAdmin() {
        if (UserConnected()) {
            if (user.isAdmin() == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isRestauAdmin() {
        if (UserConnected()) {
            if (user.isAdmin() == 1) {
                return true;
            }
        }
        return false;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
