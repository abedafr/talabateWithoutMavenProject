/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commande;
import bean.CommandeItem;
import bean.Plate;
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
    
        public List<CommandeItem> addToCart(Plate plate , List<CommandeItem> commandeItems) {

        int res = 0;
        int pos = -1;
        
        for (CommandeItem item : commandeItems) {
            pos++;
            if (item.getPlate().equals(plate)) {
                res = 1;
//                pos = cmdItems.indexOf(item);
                System.out.println("position for = " + pos);
                break;
            }
        }
        if (res == 0) {
            CommandeItem commandeItem = commandeItemFacade.addCmdItem(plate);
            commandeItems.add(commandeItem);
        } else {
            CommandeItem commandeItem = commandeItems.get(pos);
            commandeItem.setQte(commandeItem.getQte() + 1);
            commandeItem.setPrixTotalItem(commandeItem.getPrixTotalItem() + plate.getPrix());
            System.out.println("position elif = " + pos);
            commandeItems.set(pos, commandeItem);

        }
        return commandeItems;

    }
}
