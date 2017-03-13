package bean;

import bean.Menu;
import bean.Plate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-13T17:17:07")
@StaticMetamodel(Cuisine.class)
public class Cuisine_ { 

    public static volatile ListAttribute<Cuisine, Plate> plates;
    public static volatile SingularAttribute<Cuisine, Long> id;
    public static volatile ListAttribute<Cuisine, Menu> menus;
    public static volatile SingularAttribute<Cuisine, String> nom;

}