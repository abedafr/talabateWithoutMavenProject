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
import util.Session;

/**
 *
 * @author Abed
 */
@Named(value = "resultsController")
@SessionScoped
public class ResultsController implements Serializable {

    private List<Quartier> quartiers;
    private List<Restaurant> items;

    public List<Restaurant> getItems() {
        return items;
    }

    public void setItems(List<Restaurant> items) {
        items=(List<Restaurant>) Session.getAttribut("ResultHomeSearch");
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
