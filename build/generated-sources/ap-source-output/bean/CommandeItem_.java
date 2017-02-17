package bean;

import bean.Commande;
import bean.IngredientPlat;
import bean.Plate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-17T15:44:01")
@StaticMetamodel(CommandeItem.class)
public class CommandeItem_ { 

    public static volatile SingularAttribute<CommandeItem, Double> totalItem;
    public static volatile ListAttribute<CommandeItem, IngredientPlat> ingredientPlats;
    public static volatile SingularAttribute<CommandeItem, Plate> plate;
    public static volatile SingularAttribute<CommandeItem, Long> id;
    public static volatile SingularAttribute<CommandeItem, Commande> commande;

}