package bean;

import bean.CommandeItem;
import bean.Cuisine;
import bean.IngredientPlat;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-17T15:44:01")
@StaticMetamodel(Plate.class)
public class Plate_ { 

    public static volatile SingularAttribute<Plate, Integer> qte;
    public static volatile SingularAttribute<Plate, Double> prix;
    public static volatile ListAttribute<Plate, IngredientPlat> ingredientPlats;
    public static volatile SingularAttribute<Plate, Cuisine> cuisine;
    public static volatile SingularAttribute<Plate, Long> id;
    public static volatile ListAttribute<Plate, CommandeItem> commandeItems;
    public static volatile SingularAttribute<Plate, String> nom;

}