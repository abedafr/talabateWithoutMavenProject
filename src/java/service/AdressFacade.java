/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Adress;
import bean.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hp
 */
@Stateless
public class AdressFacade extends AbstractFacade<Adress> {

    @PersistenceContext(unitName = "TalabatWithoutMavenPU")
    private EntityManager em;
    @EJB
    UserFacade userFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdressFacade() {
        super(Adress.class);
    }

    public List<Adress> findAdressByUser(User user) {
        if (user != null) {
            return em.createQuery("SELECT a FROM Adress a WHERE a.user.email ='" + user.getEmail() + "'").getResultList();
        }
        return null;
    }

}
