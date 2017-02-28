package bean;

import bean.Menu;
import bean.Quartier;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-28T15:00:09")
@StaticMetamodel(Restaurant.class)
public class Restaurant_ { 

    public static volatile SingularAttribute<Restaurant, Quartier> quartier;
    public static volatile SingularAttribute<Restaurant, Long> id;
    public static volatile SingularAttribute<Restaurant, Menu> menu;
    public static volatile SingularAttribute<Restaurant, String> nom;

}