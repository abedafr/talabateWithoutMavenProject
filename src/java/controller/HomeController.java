/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Quartier;
import bean.Restaurant;
import bean.Ville;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.QuartierFacade;
import service.RestaurantFacade;
import service.VilleFacade;

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
    private Restaurant restaurant;
    @EJB
    private VilleFacade villeFacade;
    @EJB
    private QuartierFacade quartierFacade;
    @EJB
    private RestaurantFacade restaurantFacade;

    public List<Quartier> findByVille(Ville ville) {
        return quartierFacade.findByVille(ville);
    }

    public List<Restaurant> findByQuartier(Quartier quartier) {
        return restaurantFacade.findByQuartier(quartier);
    }

    public String search() {
        return "/";
    }

    public Ville getVille() {
        if (ville==null){
            ville= new Ville();
        }
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Quartier getQuartier() {
        if (quartier==null){
            quartier= new Quartier();
        }
        return quartier;
    }

    public void setQuartier(Quartier quartier) {
        this.quartier = quartier;
    }

    public Restaurant getRestaurant() {
        if (restaurant==null){
            restaurant= new Restaurant();
        }
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public VilleFacade getVilleFacade() {
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

    public RestaurantFacade getRestaurantFacade() {
        return restaurantFacade;
    }

    public void setRestaurantFacade(RestaurantFacade restaurantFacade) {
        this.restaurantFacade = restaurantFacade;
    }

}
