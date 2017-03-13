/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Quartier;
import bean.Restaurant;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import controller.util.Session;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Abed
 */
@Named(value = "resultsController")
@ViewScoped
public class ResultsController implements Serializable {

    private List<Quartier> quartiers;
    private List<Restaurant> items;
    
    public String viewMenu(Restaurant restaurant){
        Session.setAttribute(null, "ResultHomeSearch");
        Session.setAttribute(restaurant.getMenu(), "ViewMenu");
        return "/results/ViewMenu";
    }

    public List<Restaurant> getItems() {
        items = (List<Restaurant>) Session.getAttribut("ResultHomeSearch");
        return items;
    }

    public void setItems(List<Restaurant> items) {
        this.items = items;
    }

    public List<Quartier> getQuartiers() {
        return quartiers;
    }

    public void setQuartiers(List<Quartier> quartiers) {
        this.quartiers = quartiers;
    }

    /**
     * Creates a new instance of ResultsController
     */
    public ResultsController() {
    }

}
