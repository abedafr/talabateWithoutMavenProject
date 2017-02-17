package bean;

import bean.Cuisine;
import bean.Restaurant;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-17T15:44:01")
@StaticMetamodel(Menu.class)
public class Menu_ { 

    public static volatile SingularAttribute<Menu, Restaurant> restaurant;
    public static volatile SingularAttribute<Menu, Long> id;
    public static volatile ListAttribute<Menu, Cuisine> cuisines;

}