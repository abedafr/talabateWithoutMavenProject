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
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import service.CommandeItemFacade;
import util.Session;

/**
 *
 * @author Abed
 */
@Named(value = "viewMenuController")
@SessionScoped
public class ViewMenuController implements Serializable {

    private Menu menu;
    private List<Plate> items;
    private List<CommandeItem> commandeItems = new ArrayList<>();
    @EJB
    private CommandeItemFacade commandeItemFacade;

    public void addCart(Plate plate) {

        int res = 0;
        int pos = -1;
        List<CommandeItem> cmdItems = getCommandeItems();
        for (CommandeItem item : cmdItems) {
            if (item.getPlate().equals(plate)) {
                res = 1;
                pos = cmdItems.indexOf(item);
                System.out.println("position for = " + pos);
            }
        }
        if (res == 0) {
            CommandeItem commandeItem = commandeItemFacade.addCmdItem(plate);
            commandeItems.add(commandeItem);
            setCommandeItems(commandeItems);
        } else {
            CommandeItem commandeItem = commandeItems.get(pos);
            commandeItem.setQte(commandeItem.getQte() + 1);
            commandeItem.setPrixTotalItem(commandeItem.getPrixTotalItem() + plate.getPrix());
            System.out.println("position elif = " + pos);
            commandeItems.set(pos, commandeItem);

        }

    }

    public void vider() {
        commandeItems = new ArrayList<>();
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
