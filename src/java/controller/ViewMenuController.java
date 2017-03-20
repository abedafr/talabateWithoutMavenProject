/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.CommandeItem;
import bean.Menu;
import bean.Plate;
import bean.SupplementPlat;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import controller.util.Session;
import javax.enterprise.context.SessionScoped;
import service.CommandeFacade;
import service.CommandeItemFacade;
import service.SupplementPlatFacade;

/**
 *
 * @author Abed
 */
@Named(value = "viewMenuController")
@SessionScoped
public class ViewMenuController implements Serializable {

    private Menu menu;
    private Plate plate;
    private List<Plate> items;
    private CommandeItem commandeItem;
    private List<CommandeItem> commandeItems = null;
    private List<SupplementPlat> supplementPlats = null;

    @EJB
    private CommandeFacade commandeFacade;
    @EJB
    private CommandeItemFacade commandeItemFacade;
    @EJB
    private SupplementPlatFacade supplementPlatFacade;

    public void addCart(Plate plate) {
        setCommandeItems(commandeFacade.addToCart(plate, getCommandeItems(),getSupplementPlats()));
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
            if (item.getPlate().equals(commandeItem.getPlate())) {
                commandeItems.remove(item);
                break;
            }
        }
    }

    public void vider() {
        commandeItems = new ArrayList<>();
    }

    public void prepareSuplement(Plate item) {
        System.out.println(item);
        plate = item;
        plate.setSupplementPlats(supplementPlatFacade.findByPlate(plate));
        
        supplementPlats=supplementPlatFacade.findByPlate(plate);
        System.out.println(plate);
        System.out.println(plate.getSupplementPlats());

    }
    public String confirmCmd(){
        Session.setAttribute(getCommandeItems(), "ConfirmCommand");
        return "/checkout/ConfirmCommand";
    }

    public void saveSupplement() {
        getCommandeItem().setPlate(plate);
        getCommandeItem().setSupplementPlats(supplementPlats);
        
        addCart(plate);
        plate=null;
        commandeItem=null;
    }

    public List<CommandeItem> getCommandeItems() {
        if(commandeItems == null){
            commandeItems = new ArrayList<>();
        }
        return commandeItems;
    }

    public void setCommandeItems(List<CommandeItem> commandeItems) {
        this.commandeItems = commandeItems;
    }

    public Menu getMenu() {
        menu = (Menu) Session.getAttribut("ViewMenu");
        Session.setAttribute(null, "ViewMenu");
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Plate getPlate() {
        if (plate == null) {
            plate = new Plate();
        }
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    public CommandeFacade getCommandeFacade() {
        return commandeFacade;
    }

    public void setCommandeFacade(CommandeFacade commandeFacade) {
        this.commandeFacade = commandeFacade;
    }

    public CommandeItemFacade getCommandeItemFacade() {
        return commandeItemFacade;
    }

    public void setCommandeItemFacade(CommandeItemFacade commandeItemFacade) {
        this.commandeItemFacade = commandeItemFacade;
    }

    public List<Plate> getItems() {
        items = getMenu().getPlates();
        return items;
    }

    public void setItems(List<Plate> items) {
        this.items = items;
    }

    public CommandeItem getCommandeItem() {
        if (commandeItem == null) {
            commandeItem = new CommandeItem();
        }
        return commandeItem;
    }

    public void setCommandeItem(CommandeItem commandeItem) {
        this.commandeItem = commandeItem;
    }

    public List<SupplementPlat> getSupplementPlats() {
        supplementPlats = supplementPlatFacade.findByPlate(plate);
        if (supplementPlats == null) {
            supplementPlats = new ArrayList<>();
        }
        return supplementPlats;
    }

    public void setSupplementPlats(List<SupplementPlat> supplementPlats) {
        this.supplementPlats = supplementPlats;
    }

    /**
     * Creates a new instance of ViewMenuController
     */
    public ViewMenuController() {
    }

}
