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
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.CuisineFacade;
import service.QuartierFacade;
import service.RestaurantFacade;
import service.VilleFacade;
import util.Session;

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

    public void findByVille() {
        quartiers = quartierFacade.findQuartierByVille(ville);
        ville.setQuartiers(quartiers);
    }

    public String search() {
        if (quartier != null) {
            List<Restaurant> results = restaurantFacade.mainSearch(quartier, cuisine);
            System.out.println(results);
            Session.setAttribut(results, "ResultHomeSearch");
            return "/results/Results";
        } else {
            return null;
        }

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
