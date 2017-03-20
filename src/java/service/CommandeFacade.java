/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commande;
import bean.CommandeItem;
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

    public List<CommandeItem> addToCart(Plate plate, List<CommandeItem> commandeItems, List<SupplementPlat> supplementPlats) {
        int res = 0;
        int pos = -1;
        for (CommandeItem item : commandeItems) {
            pos++;
            if (item.getPlate().equals(plate)) {
                res = 1;
                System.out.println("position for = " + pos);
                break;
            }
        }
        if (res == 0) {
            CommandeItem commandeItem = commandeItemFacade.addCmdItem(plate, supplementPlats);
            if (plate.isCostume() && (supplementPlats != null || !supplementPlats.isEmpty())) {
                commandeItem.setSupplementPlats(supplementPlats);
            }
            commandeItems.add(commandeItem);
        } else {
            CommandeItem commandeItem = commandeItems.get(pos);
            commandeItem.setQte(commandeItem.getQte() + 1);
            if (!plate.isCostume()) {
                commandeItem.setPrixTotalItem(commandeItem.getPrixTotalItem() + plate.getPrix());
            }else{
                commandeItem.setPrixTotalItem(commandeItem.getPrixTotalItem() + plate.getPrix()+commandeItem.getTotalSupplements());
            }
            System.out.println("position elif = " + pos);
            commandeItems.set(pos, commandeItem);
        }
        return commandeItems;
    }

}
