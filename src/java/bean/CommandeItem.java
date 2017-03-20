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
    
    @OneToMany
    private List<SupplementPlat> supplementPlats;
    private int qte;

    @ManyToOne
    private Commande commande;
    @OneToOne
    private Plate plate;

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

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
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

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommandeItem)) {
            return false;
        }
        CommandeItem other = (CommandeItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public CommandeItem() {
    }

    public CommandeItem(Double prixTotalItem, int qte, Plate plate) {
        this.prixTotalItem = prixTotalItem;
        this.qte = qte;
        this.plate = plate;
    }

    @Override
    public String toString() {
        return "bean.CommandeItem[ id=" + id + " ]";
    }

}
