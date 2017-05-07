/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.PlatMenu;
import bean.SupplementPlat;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abed
 */
@Stateless
public class SupplementPlatFacade extends AbstractFacade<SupplementPlat> {

    @PersistenceContext(unitName = "TalabatWithoutMavenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SupplementPlatFacade() {
        super(SupplementPlat.class);
    }

    public List<SupplementPlat> findByPlatMenu(PlatMenu platMenu) {
        if (platMenu != null) {
            return em.createQuery("SELECT s FROM SupplementPlat s WHERE s.platMenu.id=" + platMenu.getId()).getResultList();
        }
        return null;
    }

    public Double CalculePrixSupplementPlat(List<SupplementPlat> supplementPlats) {
        System.out.println("==== F 3");
        Double prix = 0D;
//        if (supplementPlats == null || supplementPlats.isEmpty()) {
//            return 0D;
//        } else {
        prix = supplementPlats.stream().map((supplementPlat) -> {
            System.out.println("==== F 4");
            return supplementPlat;
        }).map((supplementPlat) -> supplementPlat.getNewPrice()).reduce(prix, (accumulator, _item) -> accumulator + _item);
//        }
        return prix;
    }
}
