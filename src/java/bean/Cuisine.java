/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Abed
 */
@Entity
public class Cuisine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @ManyToMany
    private List<Menu> menus;

    @OneToMany(mappedBy = "cuisine")
    private List<Plate> plates;

    @OneToMany(mappedBy = "cuisine")
    private List<PlatMenu> platMenus;

    public List<PlatMenu> getPlatMenus() {
        return platMenus;
    }

    public void setPlatMenus(List<PlatMenu> platMenus) {
        this.platMenus = platMenus;
    }
    
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Plate> getPlates() {
        return plates;
    }

    public void setPlates(List<Plate> plates) {
        this.plates = plates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuisine)) {
            return false;
        }
        Cuisine other = (Cuisine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cuisine " + nom;
    }

}
