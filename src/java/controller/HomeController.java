/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Cuisine;
import bean.Quartier;
import bean.Restaurant;
import bean.Ville;
import controller.util.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.CuisineFacade;
import service.QuartierFacade;
import service.RestaurantFacade;
import service.VilleFacade;
import controller.util.Session;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.faces.context.FacesContext;

/**
 *
 * @author Abed
 */
@Named(value = "homeController")
@SessionScoped
public class HomeController implements Serializable {

    /**
     * Creates a new instance of HomeController
     */
    public HomeController() {
    }

    private Ville ville;
    private Quartier quartier;
    private Cuisine cuisine;
    private List<Cuisine> cuisines;
    private List<Quartier> quartiers;
    @EJB
    private VilleFacade villeFacade;
    @EJB
    private QuartierFacade quartierFacade;

    @EJB
    private CuisineFacade cuisineFacade;
    @EJB
    private RestaurantFacade restaurantFacade;

    public void init(){
        ville =null;
        quartier=null;
        quartiers= null;
        cuisine =null;
    }
    
    public void findByVille() {
        if (ville != null) {
            quartiers = quartierFacade.findQuartierByVille(ville);
            ville.setQuartiers(quartiers);
        }
    }

    public String search() {
        if (quartier != null) {
            try {
                Session.clear();
                List<Restaurant> results = restaurantFacade.mainSearch(quartier, cuisine);
                System.out.println("RasultRestau Controller = " + results);
                Session.setAttribute(results, "ResultHomeSearch");
                FacesContext.getCurrentInstance().getExternalContext().redirect("../results/Results.xhtml");
//                return "/results/Results";
//                return ;
//        }else {
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
                return null;
            } catch (NullPointerException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                return null;

            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                return null;
            }
        }
        JsfUtil.addErrorMessage("Please Select Ville & Quartier...");
        return null;
    }

    public List<Cuisine> getCuisines() {
        cuisines = cuisineFacade.findAll();
        return cuisines;
    }

    public void setCuisines(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    public Ville getVille() {
        if (ville == null) {
            ville = new Ville();
        }
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Quartier getQuartier() {
        if (quartier == null) {
            quartier = new Quartier();
        }
        return quartier;
    }

    public void setQuartier(Quartier quartier) {
        this.quartier = quartier;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public List<Quartier> getQuartiers() {
        return quartiers;
    }

    public void setQuartiers(List<Quartier> quartiers) {
        if (quartiers == null) {
            quartiers = new ArrayList<>();
        }
        this.quartiers = quartiers;
    }

    public VilleFacade getVilleFacade() {
        if (ville == null) {
            ville = new Ville();
        }
        return villeFacade;
    }

    public void setVilleFacade(VilleFacade villeFacade) {
        this.villeFacade = villeFacade;
    }

    public QuartierFacade getQuartierFacade() {
        return quartierFacade;
    }

    public void setQuartierFacade(QuartierFacade quartierFacade) {
        this.quartierFacade = quartierFacade;
    }

    public CuisineFacade getCuisineFacade() {
        return cuisineFacade;
    }

    public void setCuisineFacade(CuisineFacade cuisineFacade) {
        this.cuisineFacade = cuisineFacade;
    }

}
