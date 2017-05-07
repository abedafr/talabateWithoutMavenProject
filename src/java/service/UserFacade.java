/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.User;
import controller.util.EmailUtil;
import controller.util.HashageUtil;
import controller.util.JsfUtil;
import controller.util.RandomStringUtil;
import controller.util.Session;
import controller.util.SessionUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Younes
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "TalabatWithoutMavenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    @Override
    public User find(Object id) {
        try {
            User user = (User) em.createQuery("select u from User u where u.email = '" + id + "'").getSingleResult();
            if (user != null) {
                return user;
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Login incorrect");
        }
        return null;
    }

    public int sendPW(String email)  {
        User user = find(email);
        if (user == null) {
            return -1;
        } else {
            String pw = RandomStringUtil.generate();
            String msg = "Bienvenu Mr. " +user.getNom()+ ",<br/>"
                    + "D'après votre demande de reinitialiser le mot de passe de votre compte FOODelivery, nous avons generer ce mot de passe pour vous.\n"
                    + "<br/><br/>"
                    + "      Nouveau Mot de Passe: <br/><center><b>"
                    + pw
                    +"</b></center><br/><br/><b><i>PS:</i></b> ce mot de passe vous donne l'acces a votre compte une seule fois, une fois que vous avez connecter il faut cree votre propre mot de passe";
            try {
                EmailUtil.sendMail("foodelivery.info@gmail.com", "foodelivery", msg, email, "Votre nouveau Mot de passe de FOODelivery");
            } catch (MessagingException ex) {
//                Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
                return -2;
            }
            
//            user.setBlocked(1);
            user.setPassword(HashageUtil.sha256(pw));
            edit(user);
            return 1;
        }
    }

    public void seDeConnnecter() {
//        historiqueConnexionFacade.createDeConnexion();
//        SessionUtil.getSession().invalidate();
        SessionUtil.deconnectUser();
//        SessionUtil.registerUser(null);

    }

    private List<User> findUsersIntersection(List<User> userAnuels, List<User> userTrims) {
        List<User> res = new ArrayList();
        res.addAll(userAnuels);
        for (User userTrim : userTrims) {
            if (res.indexOf(userTrim) == -1) {
                res.add(userTrim);
            }
        }
        return res;
    }

    public int changePassword(String login, String oldPassword, String newPassword, String newPasswordConfirmation) {
        System.out.println("voila hana dkhalt le service verifierPassword");
        User loadedeUser = find(login);

        if (!newPasswordConfirmation.equals(newPassword)) {
            return -1;
        } else if (!loadedeUser.getPassword().equals(HashageUtil.sha256(oldPassword))) {
            return -2;
        } else if (oldPassword.equals(newPassword)) {
            return -3;
        }
        loadedeUser.setPassword(HashageUtil.sha256(newPassword));
        edit(loadedeUser);
        return 1;
    }

    public void changeData(User user) {
        User loadedUser = find(user.getEmail());
        cloneData(user, loadedUser);
        edit(loadedUser);
    }

    public void cloneData(User userSource, User userDestination) {
        userDestination.setNom(userSource.getNom());
        userDestination.setPrenom(userSource.getPrenom());
        userDestination.setTel(userSource.getTel());
        userDestination.setEmail(userSource.getEmail());
    }

    public int seConnnecter(User user) {
        if (user == null || user.getEmail()== null) {
            return -5;
        } else {
            User loadedUser = find(user.getEmail());
            if (loadedUser == null) {
                return -4;
            } else if (!loadedUser.getPassword().equals(HashageUtil.sha256(user.getPassword()))) {
                if (loadedUser.getNbrCnx() < 3) {
                    System.out.println("hana loadedUser.getNbrCnx() < 3 ::: " + loadedUser.getNbrCnx());
                    loadedUser.setNbrCnx(loadedUser.getNbrCnx() + 1);
                } else if (loadedUser.getNbrCnx() >= 3) {
                    System.out.println("hana loadedUser.getNbrCnx() >= 3::: " + loadedUser.getNbrCnx());
                    loadedUser.setBlocked(true);
                }
                edit(loadedUser);
                return -3;
            } else if (loadedUser.getBlocked()) {
                return -2;
            } else {
                loadedUser.setNbrCnx(0);
                edit(loadedUser);
//                user = clone(loadedUser);
//                user.setCommune(communeFacade.findByUser(user));
//                user.setMdpChanged(loadedUser.isMdpChanged());
                user.setPassword(null);
                SessionUtil.registerUser(user);
//                historiqueConnexionFacade.createConnexion(loadedUser);
                return 1;
            }
        }
    }

//    public List<Boolean> getPrivileges() {
//        User loadeUser = find(SessionUtil.getConnectedUser().getLogin());
//        List<Boolean> privileges = new ArrayList();
//
//        privileges.add(loadeUser.isCreationRedevable()); //0
//
//        privileges.add(loadeUser.isCreationActivite()); //1
//        privileges.add(loadeUser.isCreationSecteure()); //2
//
//        privileges.add(loadeUser.isCreationUser()); //3
//
//        privileges.add(loadeUser.isTaxeBoison()); //4
//        privileges.add(loadeUser.isCloturerBoison()); //5
//
//        privileges.add(loadeUser.isTaxeSejour()); //6
//        privileges.add(loadeUser.isCloturerSejour()); //7
//
//        privileges.add(loadeUser.isTaxe38()); //8
//        privileges.add(loadeUser.isCloturer38()); //9
//
//        privileges.add(loadeUser.isTaxe37()); //10
//        privileges.add(loadeUser.isCloturer37()); //11
//
//        privileges.add(loadeUser.isTaxeTnb()); //12
//        privileges.add(loadeUser.isCloturerTnb()); //13
//
//        privileges.add(loadeUser.isTaux());//14
//        privileges.add(loadeUser.isAdmin()); //15
//        privileges.add(loadeUser.isCreationRedevable());//16
//
//        privileges.add(loadeUser.isTaxeTransport());//17
//
//        return privileges;
//    }
//    public User clone(User user) {
//        User clone = new User();
//        clone.setLogin(user.getLogin());
//        clone.setBlocked(user.getBlocked());
//        clone.setCloturer37(user.isCloturer37());
//        clone.setCloturer38(user.isCloturer38());
//        clone.setCloturerBoison(user.isCloturerBoison());
//        clone.setCloturerSejour(user.isCloturerSejour());
//        clone.setCloturerTnb(user.isCloturerTnb());
//        clone.setCommune(user.getCommune());
//        clone.setCreationActivite(user.isCreationActivite());
//        clone.setCreationRedevable(user.isCreationRedevable());
//        clone.setCreationSecteure(user.isCreationSecteure());
//        clone.setCreationUser(user.isCreationUser());
//        clone.setEmail(user.getEmail());
//        clone.setMdpChanged(user.isMdpChanged());
//        clone.setNbrCnx(user.getNbrCnx());
//        clone.setNom(user.getNom());
//        clone.setPassword(user.getPassword());
//        clone.setPrenom(user.getPrenom());
//        clone.setTaux(user.isTaux());
//        clone.setTaxe37(user.isTaxe37());
//        clone.setTaxe38(user.isTaxe38());
//        clone.setTaxeBoison(user.isTaxeBoison());
//        clone.setTaxeSejour(user.isTaxeSejour());
//        clone.setTaxeTnb(user.isTaxeTnb());
//        clone.setTel(user.getTel());
//        clone.setAdmin(user.isAdmin());
//        return clone;
//    }
//    @Override
//    public void create(User user) {
//        user.setCommune(SessionUtil.getCurrentCommune());
//        super.create(user);
//        SessionUtil.getCurrentCommune().getUsers().add(user);
//
//    }
//    @Override
//    public void edit(User user) {
//        super.edit(user);
//        int index = SessionUtil.getCurrentCommune().getUsers().indexOf(user);
//        if (index != -1) {
//            SessionUtil.getCurrentCommune().getUsers().set(index, user);
//        }
//    }
//
//    @Override
//    public void remove(User user) {
//        super.remove(user);
//        int index = SessionUtil.getCurrentCommune().getUsers().indexOf(user);
//        if (index != -1) {
//            SessionUtil.getCurrentCommune().getUsers().remove(index);
//        }
//    }
//
//    @Override
//    public List<User> findAll() {
//        return findByCommune(SessionUtil.getCurrentCommune());
//    }
}
