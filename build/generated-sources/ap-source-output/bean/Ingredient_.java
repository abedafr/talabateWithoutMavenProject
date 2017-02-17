package bean;

import bean.IngredientPlat;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-17T15:44:01")
@StaticMetamodel(Ingredient.class)
public class Ingredient_ { 

    public static volatile SingularAttribute<Ingredient, Double> defaultPrice;
    public static volatile ListAttribute<Ingredient, IngredientPlat> ingredientPlats;
    public static volatile SingularAttribute<Ingredient, Long> id;
    public static volatile SingularAttribute<Ingredient, String> nom;

}