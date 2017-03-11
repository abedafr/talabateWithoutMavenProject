/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.CommandeItem;
import bean.Menu;
import bean.Plate;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import controller.util.Session;
import javax.faces.view.ViewScoped;
import service.CommandeFacade;
import service.CommandeItemFacade;

/**
 *
 * @author Abed
 */
@Named(value = "viewMenuController")
@ViewScoped
public class ViewMenuController implements Serializable {

    private Menu menu;
    private List<Plate> items;
    private List<CommandeItem> commandeItems = new ArrayList<>();

    @EJB
    private CommandeFacade commandeFacade;
    @EJB
    private CommandeItemFacade commandeItemFacade;

    public void addCart(Plate plate) {
        setCommandeItems(commandeFacade.addToCart(plate, commandeItems));
    }

    public void plus(CommandeItem commandeItem) {
        commandeItem.setQte(commandeItem.getQte() + 1);
        commandeItem.setPrixTotalItem(commandeItem.getPrixTotalItem() + commandeItem.getPlate().getPrix());
    }

    public void minus(CommandeItem commandeItem) {
        if (commandeItem.getQte() != 1) {
            commandeItem.setQte(commandeItem.getQte() - 1);
            commandeItem.setPrixTotalItem(commandeItem.getPrixTotalItem() - commandeItem.getPlate().getPrix());
        } else {
            delete(commandeItem);
        }
    }

    public void delete(CommandeItem commandeItem) {
        
        for (CommandeItem item : commandeItems) {
          
            if(item.equals(commandeItem)){
                commandeItems.remove(commandeItem);
                break;
            }
        }
    }

    public void vider() {
        commandeItems = new ArrayList<>();
    }
    
    public void prepareSuplement(){
        
    }

    public List<CommandeItem> getCommandeItems() {
        return commandeItems;
    }

    public void setCommandeItems(List<CommandeItem> commandeItems) {
        this.commandeItems = commandeItems;
    }

    public Menu getMenu() {
        menu = (Menu) Session.getAttribut("ViewMenu");
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Plate> getItems() {
        items = getMenu().getPlates();
        return items;
    }

    public void setItems(List<Plate> items) {
        this.items = items;
    }

    /**
     * Creates a new instance of ViewMenuController
     */
    public ViewMenuController() {
    }

}
