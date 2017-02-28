package bean;

import bean.CommandeItem;
import bean.Cuisine;
import bean.Menu;
import bean.SupplementPlat;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-28T15:00:09")
@StaticMetamodel(Plate.class)
public class Plate_ { 

    public static volatile SingularAttribute<Plate, Double> prix;
    public static volatile SingularAttribute<Plate, CommandeItem> commandeItem;
    public static volatile SingularAttribute<Plate, Cuisine> cuisine;
    public static volatile ListAttribute<Plate, SupplementPlat> supplementPlats;
    public static volatile SingularAttribute<Plate, Long> id;
    public static volatile SingularAttribute<Plate, Menu> menu;
    public static volatile SingularAttribute<Plate, String> nom;

}