/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


/**
 *
 * @author moulaYounes
 */
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String login;
    private String password;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String gender;
    private LocalDate dateNaissance;
    private int blocked;
    private int nbrCnx;
    private boolean mdpChanged;
    private boolean admiin;
    private int tentativeRest;
    
    
    @ManyToMany
    private List<Commande> commandes;
    
    @OneToMany(mappedBy = "user")
    private List<Adress> adresss;
    @OneToMany(mappedBy = "user")
    private List<Device> devices;

    public List<Device> getDevices() {
        if(devices==null){
            devices= new ArrayList<>();
        }
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public boolean isAdmin() {
        return admiin;
    }

    public void setAdmin(boolean admiin) {
        this.admiin = admiin;
    }

    public List<Adress> getAdresss() {
        return adresss;
    }

    public void setAdresss(List<Adress> adresss) {
        this.adresss = adresss;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    

    public User(String login) {
        this.login = login;
    }

    public User() {

    }

    public boolean isMdpChanged() {
        return mdpChanged;
    }

    public void setMdpChanged(boolean mdpChanged) {
        this.mdpChanged = mdpChanged;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getBlocked() {
        return blocked;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    
    public int getNbrCnx() {
        return nbrCnx;
    }

    public void setNbrCnx(int nbrCnx) {
        this.nbrCnx = nbrCnx;
    }

    public boolean isAdmiin() {
        return admiin;
    }

    public void setAdmiin(boolean admiin) {
        this.admiin = admiin;
    }

    public int getTentativeRest() {
        return tentativeRest;
    }

    public void setTentativeRest(int tentativeRest) {
        this.tentativeRest = tentativeRest;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.login);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return true;
    }
    
    

    
    @Override
    public String toString() {
        return login;
    }
}
