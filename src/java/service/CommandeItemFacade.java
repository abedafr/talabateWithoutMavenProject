/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.CommandeItem;
import bean.PlatMenu;
import bean.Plate;
import bean.SupplementPlat;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abed
 */
@Stateless
public class CommandeItemFacade extends AbstractFacade<CommandeItem> {

    @PersistenceContext(unitName = "TalabatWithoutMavenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandeItemFacade() {
        super(CommandeItem.class);
    }

    @EJB
    private SupplementPlatFacade supplementPlatFacade;

    public CommandeItem addCmdItem(PlatMenu platMenu, List<SupplementPlat> supplementPlats) {
        System.out.println("==== F 2");
        CommandeItem commandeItem;
        if (platMenu.isCostume()) {
            Double prix = platMenu.getPrix() + supplementPlatFacade.CalculePrixSupplementPlat(supplementPlats);
            commandeItem = new CommandeItem(prix, 1, platMenu);
            commandeItem.setTotalSupplements(supplementPlatFacade.CalculePrixSupplementPlat(supplementPlats));
            System.out.println("==== F 5");
        } else {
            commandeItem = new CommandeItem(platMenu.getPrix(), 1, platMenu);
        }
        return commandeItem;
    }

    public void clone(CommandeItem commandeItemSource, CommandeItem commandeItemDestination) {
//        commandeItemDestination=commandeItemSource;
        commandeItemDestination.setCommande(commandeItemSource.getCommande());
        commandeItemDestination.setId(commandeItemSource.getId());
        commandeItemDestination.setPlatMenu(commandeItemSource.getPlatMenu());
        commandeItemDestination.setPrixTotalItem(commandeItemSource.getPrixTotalItem());
        commandeItemDestination.setQte(commandeItemSource.getQte());
        commandeItemDestination.setSupplementSelecteds(commandeItemSource.getSupplementSelecteds());

    }

    public CommandeItem clone(CommandeItem commandeItem) {
        CommandeItem cloned = new CommandeItem();
        clone(commandeItem, cloned);
        return cloned;
    }

}
