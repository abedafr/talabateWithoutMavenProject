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
public class PlatMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double prix;
    private boolean costume;

    @OneToOne(mappedBy = "platMenu")
    private CommandeItem commandeItem;

    @OneToMany(mappedBy = "platMenu")
    private List<SupplementPlat> supplementPlats;

    @ManyToOne
    private Menu menu;
    @ManyToOne
    private Cuisine cuisine;
    @ManyToOne
    private Plate plate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    public boolean isCostume() {
        return costume;
    }

    public void setCostume(boolean costume) {
        this.costume = costume;
    }

    public List<SupplementPlat> getSupplementPlats() {
        return supplementPlats;
    }

    public void setSupplementPlats(List<SupplementPlat> supplementPlats) {
        this.supplementPlats = supplementPlats;
    }

    
    public CommandeItem getCommandeItem() {
        return commandeItem;
    }

    public void setCommandeItem(CommandeItem commandeItem) {
        this.commandeItem = commandeItem;
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
        if (!(object instanceof PlatMenu)) {
            return false;
        }
        PlatMenu other = (PlatMenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.PlatMenu[ id=" + id + " ]";
    }

}
