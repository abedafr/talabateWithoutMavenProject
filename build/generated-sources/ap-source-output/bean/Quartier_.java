package bean;

import bean.Restaurant;
import bean.Ville;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-17T15:44:01")
@StaticMetamodel(Quartier.class)
public class Quartier_ { 

    public static volatile SingularAttribute<Quartier, Ville> ville;
    public static volatile ListAttribute<Quartier, Restaurant> restaurants;
    public static volatile SingularAttribute<Quartier, Long> id;
    public static volatile SingularAttribute<Quartier, String> nom;

}