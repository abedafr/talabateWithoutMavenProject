/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Quartier;
import bean.Ville;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import service.QuartierFacade;

/**
 *
 * @author Abed
 */
@Named(value = "manageRestau")
@SessionScoped
public class ManageRestau implements Serializable {

    @EJB
    private QuartierFacade quartierFacade;
    
    private Ville ville;
    private Quartier quartier;

    
    public void findByVille() {
        if (ville != null) {
            ville.setQuartiers(quartierFacade.findQuartierByVille(ville));
        }
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
        return quartier;
    }

    public void setQuartier(Quartier quartier) {
        this.quartier = quartier;
    }

    /**
     * Creates a new instance of ManageRestau
     */
    public ManageRestau() {
    }

}
