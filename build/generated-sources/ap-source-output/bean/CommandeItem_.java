package bean;

import bean.Commande;
import bean.Plate;
import bean.SupplementSelected;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-28T15:00:09")
@StaticMetamodel(CommandeItem.class)
public class CommandeItem_ { 

    public static volatile SingularAttribute<CommandeItem, Integer> qte;
    public static volatile SingularAttribute<CommandeItem, Plate> plate;
    public static volatile SingularAttribute<CommandeItem, Long> id;
    public static volatile SingularAttribute<CommandeItem, Double> prixTotalItem;
    public static volatile SingularAttribute<CommandeItem, Commande> commande;
    public static volatile ListAttribute<CommandeItem, SupplementSelected> supplementSelecteds;

}