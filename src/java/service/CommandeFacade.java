/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Adress;
import bean.Commande;
import bean.CommandeItem;
import bean.PlatMenu;
import bean.Restaurant;
import bean.SupplementPlat;
import bean.User;
import java.time.LocalDateTime;
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
    @EJB
    private UserFacade userFacade;
    @EJB
    private AdressFacade adressFacade;

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

    public Commande createCommande(Commande commande, List<CommandeItem> commandeItems, Restaurant restaurant, User user, Adress adress) {
        commande = new Commande();

        commande.setId(generateId("Commande", "id"));
        commande.setDateCmd(LocalDateTime.now());
        commande.setAdress(adress);
        commande.setUser(user);
        commande.setRestaurant(restaurant);
        commande.setTotal(calculTotalCommande(commandeItems));

        create(commande);
        userFacade.edit(user);
        adress.setCommande(commande);
        adressFacade.edit(adress);
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
    
    public List<Commande> findCommandByUser(User user){
        if(user!=null){
            return em.createQuery("SELECT c FROM Commande c WHERE c.user.email ='"+user.getEmail()+"'").getResultList();
        }
        return null;
    }
    
}
