/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Plate;
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

    public List<SupplementPlat> findByPlate(Plate plate) {
        if (plate != null) {
            return em.createQuery("SELECT s FROM SupplementPlat s WHERE s.plate.id=" + plate.getId()).getResultList();
        }
        return null;
    }

    public Double CalculePrixSupplementPlat(List<SupplementPlat> supplementPlats) {
        Double prix = 0D;
        if (supplementPlats == null || supplementPlats.isEmpty()) {
            return 0D;
        }
        for (SupplementPlat supplementPlat : supplementPlats) {
            prix += supplementPlat.getNewPrice();
        }
        return prix;
    }
}
