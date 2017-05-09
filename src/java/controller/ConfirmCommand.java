/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Adress;
import bean.Commande;
import bean.CommandeItem;
import bean.Menu;
import bean.Quartier;
import bean.User;
import bean.Ville;
import controller.util.JsfUtil;
import controller.util.Session;
import controller.util.SessionUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import service.AdressFacade;
import service.CommandeFacade;
import service.QuartierFacade;
import service.UserFacade;

/**
 *
 * @author BMA
 */
@Named(value = "confirmCommand")
@SessionScoped
public class ConfirmCommand implements Serializable {

    private Adress adress = null;
    private CommandeItem commandeItem;
    private List<CommandeItem> commandeItems;
    private Commande commande;
    private Ville ville;
    private Quartier quartier;
    private User user;
    private List<Quartier> quartiers;
    private Menu menu = (Menu) Session.getAttribut("ViewMenu");
    @EJB
    private service.CommandeFacade commandeFacade;
    @EJB
    private service.QuartierFacade quartierFacade;
    @EJB
    private service.UserFacade userFacade;
    @EJB
    private service.AdressFacade adressFacade;

    public void createAderess() {
        List<Adress> adresses = new ArrayList<>();
        adress.setId(adressFacade.generateId("Adress", "id"));
        adress.setUser(user);
        adress.setVille(ville);
        adress.setQuartier(quartier);
        adresses.add(adress);
        user.setAdresss(adresses);
    }

    public void home() throws IOException {
        commandeItems=null;
        commande=null;
        Session.delete("ConfirmCommand");
        Session.delete("ResultHomeSearch");
        Session.delete("ViewMenu");
        Session.clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("../home/Home.xhtml");
    }

    public void findByVille() {
        if (ville != null) {
            quartiers = quartierFacade.findQuartierByVille(ville);
            ville.setQuartiers(quartiers);
        }
    }

    public void finish() throws IOException {
        if (adress != null) {
            commande = commandeFacade.createCommande(commande, commandeItems, menu.getRestaurant(), user, adress);
            commandeItem=null;
            adress=null;
            user=null;
            FacesContext.getCurrentInstance().getExternalContext().redirect("../checkout/ThankYou.xhtml");
        }else{
            JsfUtil.addErrorMessage("Ajouter une adress pour confirmer la commande");
        }

    }

    public double calculTotal() {
        return commandeFacade.calculTotalCommande(commandeItems);

    }

    public void prepareAdress() {
        adress = new Adress();
    }

    public CommandeItem getCommandeItem() {
        return commandeItem;
    }

    public void setCommandeItem(CommandeItem commandeItem) {
        this.commandeItem = commandeItem;
    }

    public List<CommandeItem> getCommandeItems() {
        commandeItems = (List<CommandeItem>) Session.getAttribut("ConfirmCommand");
        return commandeItems;
    }

    public void setCommandeItems(List<CommandeItem> commandeItems) {
        this.commandeItems = commandeItems;
    }

    public Adress getAdress() {
//        if (adress == null) {
//            adress = new Adress();
//        }
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Ville getVille() {
        ville = getQuartier().getVille();
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Quartier getQuartier() {

        quartier = menu.getRestaurant().getQuartier();
        return quartier;
    }

    public void setQuartier(Quartier quartier) {
        this.quartier = quartier;
    }

    public List<Quartier> getQuartiers() {
        quartiers = quartierFacade.findQuartierByVille(getVille());
        return quartiers;
    }

    public void setQuartiers(List<Quartier> quartiers) {
        this.quartiers = quartiers;
    }

    public CommandeFacade getCommandeFacade() {
        return commandeFacade;
    }

    public void setCommandeFacade(CommandeFacade commandeFacade) {
        this.commandeFacade = commandeFacade;
    }

    public QuartierFacade getQuartierFacade() {
        return quartierFacade;
    }

    public void setQuartierFacade(QuartierFacade quartierFacade) {
        this.quartierFacade = quartierFacade;
    }

    public AdressFacade getAdressFacade() {
        return adressFacade;
    }

    public void setAdressFacade(AdressFacade adressFacade) {
        this.adressFacade = adressFacade;
    }

    public User getUser() {
        if (SessionUtil.getConnectedUser() != null) {
            user = userFacade.find(SessionUtil.getConnectedUser().getEmail());
        }
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    /**
     * Creates a new instance of ConfirmCommand
     */
}
