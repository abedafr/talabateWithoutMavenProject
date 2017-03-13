package bean;

import bean.SupplementPlat;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-13T17:17:07")
@StaticMetamodel(Supplement.class)
public class Supplement_ { 

    public static volatile SingularAttribute<Supplement, Double> defaultPrice;
    public static volatile ListAttribute<Supplement, SupplementPlat> supplementPlats;
    public static volatile SingularAttribute<Supplement, Long> id;
    public static volatile SingularAttribute<Supplement, String> nom;

}