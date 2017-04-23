/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.User;
import controller.util.Session;
import controller.util.SessionUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Abed
 */
@Named(value = "templateController")
@SessionScoped
public class TemplateController implements Serializable {

    private User user;
    /**
     * Creates a new instance of TemplateController
     */
    public TemplateController() {
    }

    public boolean UserConnected() {
        user = SessionUtil.getConnectedUser();
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isAdmin(){
        if(UserConnected()){
            if (user.isAdmin()==2){
                return true;
            }
        }
        return false;
    }
    public boolean isRestauAdmin(){
        if(UserConnected()){
            if (user.isAdmin()==1){
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
