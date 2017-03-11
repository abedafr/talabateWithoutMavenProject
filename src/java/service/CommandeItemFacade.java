/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.CommandeItem;
import bean.Plate;
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
    
    public CommandeItem addCmdItem(Plate plate){
        CommandeItem commandeItem = new CommandeItem(plate.getPrix(), 1, plate);
        return commandeItem;
    }
    
    public void clone(CommandeItem commandeItemSource, CommandeItem commandeItemDestination){
//        commandeItemDestination=commandeItemSource;
        commandeItemDestination.setCommande(commandeItemSource.getCommande());
        commandeItemDestination.setId(commandeItemSource.getId());
        commandeItemDestination.setPlate(commandeItemSource.getPlate());
        commandeItemDestination.setPrixTotalItem(commandeItemSource.getPrixTotalItem());
        commandeItemDestination.setQte(commandeItemSource.getQte());
        commandeItemDestination.setSupplementSelecteds(commandeItemSource.getSupplementSelecteds());
        
    }
    
    public CommandeItem clone(CommandeItem commandeItem){
        CommandeItem cloned = new CommandeItem();
        clone(commandeItem, cloned);
        return cloned;
    }
    
}
