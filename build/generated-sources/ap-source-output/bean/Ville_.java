package bean;

import bean.Quartier;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-13T17:17:07")
@StaticMetamodel(Ville.class)
public class Ville_ { 

    public static volatile SingularAttribute<Ville, Long> id;
    public static volatile ListAttribute<Ville, Quartier> quartiers;
    public static volatile SingularAttribute<Ville, String> nom;

}