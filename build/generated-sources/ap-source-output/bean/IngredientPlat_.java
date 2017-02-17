package bean;

import bean.CommandeItem;
import bean.Ingredient;
import bean.Plate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-17T15:44:01")
@StaticMetamodel(IngredientPlat.class)
public class IngredientPlat_ { 

    public static volatile SingularAttribute<IngredientPlat, Ingredient> ingredient;
    public static volatile SingularAttribute<IngredientPlat, Double> prix;
    public static volatile SingularAttribute<IngredientPlat, Plate> plate;
    public static volatile SingularAttribute<IngredientPlat, Long> id;
    public static volatile ListAttribute<IngredientPlat, CommandeItem> commandeItems;

}