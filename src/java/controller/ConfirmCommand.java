/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Adress;
import bean.Commande;
import bean.CommandeItem;
import controller.util.Session;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

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
  
    @EJB
    private service.CommandeFacade commandeFacade;
    @EJB
    private service.AdressFacade adressFacade;

    public void createAderess() {
        adressFacade.create(adress);
    }
 
 
    public String finish() {

        return "/checkout/ThankYou";
    }

    public double calculTotal() {
        return commandeFacade.calculTotalCommande(commandeItems);

    }


    public String expressCheckout() {
       
        return "/checkout/ConfirmCommand";
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

    /**
     * Creates a new instance of ConfirmCommand
     */
   
}
