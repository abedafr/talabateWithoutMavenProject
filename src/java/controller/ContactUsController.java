/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Restaurant;
import bean.User;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.RestaurantFacade;
import service.UserFacade;

/**
 *
 * @author hp
 */
@Named(value = "contactUsController")
@SessionScoped
public class ContactUsController implements Serializable {

    @EJB
    private UserFacade ejbFacade;
    private User userSelected; 

    private Restaurant restSelected;
    @EJB
    private RestaurantFacade ejbRestFacade;

    public String create() {
        userSelected.setBlocked(true);
        restSelected.setAccepted(false);
        ejbFacade.create(userSelected);
        ejbRestFacade.create(restSelected);
        return "/home/Home.xhtml";

    }

    public UserFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(UserFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public User getUserSelected() {
        if (userSelected == null) {
            userSelected = new User();
        }
        return userSelected;
    }

    public void setUserSelected(User userSelected) {
        this.userSelected = userSelected;
    }

    public Restaurant getRestSelected() {
        if (restSelected == null) {
            restSelected = new Restaurant();
        }
        return restSelected;
    }

    public void setRestSelected(Restaurant restSelected) {
        this.restSelected = restSelected;
    }

    public RestaurantFacade getEjbRestFacade() {
        return ejbRestFacade;
    }

    public void setEjbRestFacade(RestaurantFacade ejbRestFacade) {
        this.ejbRestFacade = ejbRestFacade;
    }

    /**
     * Creates a new instance of ContactUsController
     */
    public ContactUsController() {
    }

}
