/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commande;
import bean.CommandeItem;
import bean.PlatMenu;
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
public class CommandeFacade extends AbstractFacade<Commande> {

    @PersistenceContext(unitName = "TalabatWithoutMavenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @EJB
    private CommandeItemFacade commandeItemFacade;

    public CommandeFacade() {
        super(Commande.class);
    }

    public List<CommandeItem> addToCart(PlatMenu platMenu, List<CommandeItem> commandeItems, List<SupplementPlat> supplementPlats) {
        int res = 0;
        int pos = -1;
        for (CommandeItem item : commandeItems) {
            pos++;
            if (item.getPlatMenu().equals(platMenu)) {
                res = 1;
                System.out.println("position for = " + pos);
                break;
            }
        }
        if (res == 0) {
            System.out.println("==== F 1");
            CommandeItem commandeItem = commandeItemFacade.addCmdItem(platMenu, supplementPlats);
            if (platMenu.isCostume() && (supplementPlats != null || !supplementPlats.isEmpty())) {
                System.out.println("==== F 6");
                System.out.println("costume plate");
                commandeItem.setSupplementPlats(supplementPlats);
            }
            commandeItems.add(commandeItem);
        } else {
            CommandeItem commandeItem = commandeItems.get(pos);
            commandeItem.setQte(commandeItem.getQte() + 1);
            if (!platMenu.isCostume()) {
                commandeItem.setPrixTotalItem(commandeItem.getPrixTotalItem() + platMenu.getPrix());
            } else {
                commandeItem.setPrixTotalItem(commandeItem.getPrixTotalItem() + platMenu.getPrix() + commandeItem.getTotalSupplements());
            }
            System.out.println("position elif = " + pos);
            commandeItems.set(pos, commandeItem);
        }
        return commandeItems;
    }

    public Commande createCommande(Commande commande, List<CommandeItem> commandeItems) {
        commande.setId(generateId("Commande", "id"));
        create(commande);

        for (CommandeItem commandeItem : commandeItems) {
            commandeItem.setCommande(commande);
            commandeItemFacade.create(commandeItem);
        }
        return commande;
    }

    public double calculTotalCommande(List<CommandeItem> commandeItems) {
        double tot = 0;
        if (commandeItems != null) {
            for (CommandeItem commandeItem : commandeItems) {
                tot = tot + commandeItem.getPrixTotalItem();
            }
        }
        return tot;
    }

}
