/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Abed
 */
@Entity
public class CommandeItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double prixTotalItem;
    private Double totalSupplements;
    private int qte;
    
    
    @OneToMany
    private List<SupplementPlat> supplementPlats;

    @ManyToOne
    private Commande commande;
    
    @OneToOne
    private PlatMenu platMenu;

    @OneToMany(mappedBy = "commandeItem")
    private List<SupplementSelected> supplementSelecteds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalSupplements() {
        return totalSupplements;
    }

    public void setTotalSupplements(Double totalSupplements) {
        this.totalSupplements = totalSupplements;
    }

    public Double getPrixTotalItem() {
        return prixTotalItem;
    }

    public void setPrixTotalItem(Double prixTotalItem) {
        this.prixTotalItem = prixTotalItem;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public List<SupplementSelected> getSupplementSelecteds() {
        return supplementSelecteds;
    }

    public void setSupplementSelecteds(List<SupplementSelected> supplementSelecteds) {
        this.supplementSelecteds = supplementSelecteds;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public List<SupplementPlat> getSupplementPlats() {
        return supplementPlats;
    }

    public void setSupplementPlats(List<SupplementPlat> supplementPlats) {
        this.supplementPlats = supplementPlats;
    }

    public PlatMenu getPlatMenu() {
        return platMenu;
    }

    public void setPlatMenu(PlatMenu platMenu) {
        this.platMenu = platMenu;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
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
        final CommandeItem other = (CommandeItem) obj;
        if (!Objects.equals(this.platMenu, other.platMenu)) {
            return false;
        }
        return true;
    }

    

    public CommandeItem() {
    }

    public CommandeItem(Double prixTotalItem, int qte, PlatMenu platMenu) {
        this.prixTotalItem = prixTotalItem;
        this.qte = qte;
        this.platMenu = platMenu;
    }

    @Override
    public String toString() {
        return "bean.CommandeItem[ id=" + id + " ]";
    }

}
