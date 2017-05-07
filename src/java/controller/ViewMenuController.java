/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.CommandeItem;
import bean.Menu;
import bean.PlatMenu;
import bean.SupplementPlat;
import bean.User;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import controller.util.Session;
import controller.util.SessionUtil;
import java.io.IOException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
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
    private List<SupplementPlat> allSupplementsPlats = null;
    private List<SupplementPlat> selectedSupplementsPlats = null;

    @EJB
    private CommandeFacade commandeFacade;
    @EJB
    private CommandeItemFacade commandeItemFacade;
    @EJB
    private SupplementPlatFacade supplementPlatFacade;

    public void addCart(PlatMenu platMenu) {
        setCommandeItems(commandeFacade.addToCart(platMenu, getCommandeItems(), getSelectedSupplementsPlats()));
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
//        allSupplementsPlats=supplementPlatFacade.findByPlate(platMenu);
        System.out.println(platMenu);
        System.out.println("All " + getAllSupplementsPlats());
        System.out.println("Selectd pre " + getSelectedSupplementsPlats());

    }

    public void confirmCmd() throws IOException {
        Session.setAttribute(getCommandeItems(), "ConfirmCommand");
        FacesContext.getCurrentInstance().getExternalContext().redirect("../checkout/Paiment.xhtml");
    }

    public double calculTotal() {
        return commandeFacade.calculTotalCommande(commandeItems);
    }

    public void saveSupplement() {
        System.out.println("==== C 1");
        System.out.println(platMenu);
//        System.out.println(allSupplementsPlats);
        getCommandeItem().setPlatMenu(platMenu);
//        getCommandeItem().setSupplementPlats(allSupplementsPlats);
        System.out.println("Selectd " + getSelectedSupplementsPlats());
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

    public List<SupplementPlat> getSelectedSupplementsPlats() {
        if (selectedSupplementsPlats == null) {
            selectedSupplementsPlats = new ArrayList<>();
        }
        return selectedSupplementsPlats;
    }

    public void setSelectedSupplementsPlats(List<SupplementPlat> selectedSupplementsPlats) {
        this.selectedSupplementsPlats = selectedSupplementsPlats;
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

    public List<SupplementPlat> getAllSupplementsPlats() {
        allSupplementsPlats = supplementPlatFacade.findByPlatMenu(getPlatMenu());
        if (allSupplementsPlats == null) {
            allSupplementsPlats = new ArrayList<>();
        }
        return allSupplementsPlats;
    }

    public void setAllSupplementsPlats(List<SupplementPlat> allSupplementsPlats) {
        this.allSupplementsPlats = allSupplementsPlats;
    }

    /**
     * Creates a new instance of ViewMenuController
     */
    public ViewMenuController() {
    }

}
