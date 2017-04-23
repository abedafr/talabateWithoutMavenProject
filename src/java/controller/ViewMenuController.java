/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.CommandeItem;
import bean.Menu;
import bean.PlatMenu;
import bean.Plate;
import bean.SupplementPlat;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import controller.util.Session;
import controller.util.SessionUtil;
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
    private PlatMenu platMenu;
    private List<PlatMenu> items;
    private CommandeItem commandeItem;
    private List<CommandeItem> commandeItems = null;
    private List<SupplementPlat> AllSupplementPlats = null;
    private List<SupplementPlat> SelecedSupplementPlats = null;

    @EJB
    private CommandeFacade commandeFacade;
    @EJB
    private CommandeItemFacade commandeItemFacade;
    @EJB
    private SupplementPlatFacade supplementPlatFacade;

    public void addCart(PlatMenu platMenu) {
        setCommandeItems(commandeFacade.addToCart(platMenu, getCommandeItems(), getSelecedSupplementPlats()));
    }

    public void plus(CommandeItem commandeItem) {
        commandeItem.setQte(commandeItem.getQte() + 1);
        commandeItem.setPrixTotalItem(commandeItem.getPrixTotalItem() + commandeItem.getPlatMenu().getPrix());
    }

    public void minus(CommandeItem commandeItem) {
        if (commandeItem.getQte() != 1) {
            commandeItem.setQte(commandeItem.getQte() - 1);
            commandeItem.setPrixTotalItem(commandeItem.getPrixTotalItem() - commandeItem.getPlatMenu().getPrix());
        } else {
            delete(commandeItem);
        }
    }

    public void delete(CommandeItem commandeItem) {
        for (CommandeItem item : commandeItems) {
            if (item.equals(commandeItem)) {
                commandeItems.remove(item);
                break;
            }
        }
    }

    public void vider() {
        commandeItems = new ArrayList<>();
    }

    public void prepareSuplement(PlatMenu item) {
        System.out.println(item);
        platMenu = item;
//        platMenu.setSupplementPlats(supplementPlatFacade.findByPlate(platMenu));

//        AllSupplementPlats=supplementPlatFacade.findByPlate(platMenu);
        System.out.println(platMenu);
        System.out.println("All " + getAllSupplementPlats());
        System.out.println("Selectd pre " + getSelecedSupplementPlats());

    }

    public String confirmCmd(){
        Session.setAttribute(getCommandeItems(), "ConfirmCommand");
        return "/checkout/Paiment";
    }
    
    public double calculTotal(){
        return commandeFacade.calculTotalCommande(commandeItems);
    }

    public void saveSupplement() {
        System.out.println("==== C 1");
        System.out.println(platMenu);
//        System.out.println(AllSupplementPlats);
        getCommandeItem().setPlatMenu(platMenu);
//        getCommandeItem().setSupplementPlats(AllSupplementPlats);
        System.out.println("Selectd " + getSelecedSupplementPlats());
        addCart(platMenu);

//        platMenu=null;
//        commandeItem=null;
    }

    public List<CommandeItem> getCommandeItems() {
        if (commandeItems == null) {
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

    public PlatMenu getPlatMenu() {
        if (platMenu == null) {
            platMenu = new PlatMenu();
        }
        return platMenu;
    }

    public void setPlatMenu(PlatMenu platMenu) {
        this.platMenu = platMenu;
    }

    public List<SupplementPlat> getSelecedSupplementPlats() {
        if (SelecedSupplementPlats == null) {
            SelecedSupplementPlats = new ArrayList<>();
        }
        return SelecedSupplementPlats;
    }

    public void setSelecedSupplementPlats(List<SupplementPlat> SelecedSupplementPlats) {
        this.SelecedSupplementPlats = SelecedSupplementPlats;
    }

    public SupplementPlatFacade getSupplementPlatFacade() {
        return supplementPlatFacade;
    }

    public void setSupplementPlatFacade(SupplementPlatFacade supplementPlatFacade) {
        this.supplementPlatFacade = supplementPlatFacade;
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

    public List<PlatMenu> getItems() {
        items = getMenu().getPlatMenus();
//        SessionUtil.getSession().invalidate();
        return items;
    }

    public void setItems(List<PlatMenu> items) {
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

    public List<SupplementPlat> getAllSupplementPlats() {
        AllSupplementPlats = supplementPlatFacade.findByPlate(getPlatMenu().getPlate());
        if (AllSupplementPlats == null) {
            AllSupplementPlats = new ArrayList<>();
        }
        return AllSupplementPlats;
    }

    public void setAllSupplementPlats(List<SupplementPlat> AllSupplementPlats) {
        this.AllSupplementPlats = AllSupplementPlats;
    }

    /**
     * Creates a new instance of ViewMenuController
     */
    public ViewMenuController() {
    }

}
