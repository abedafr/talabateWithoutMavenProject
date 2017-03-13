package bean;

import bean.CommandeItem;
import bean.User;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-13T17:17:07")
@StaticMetamodel(Commande.class)
public class Commande_ { 

    public static volatile SingularAttribute<Commande, Double> total;
    public static volatile SingularAttribute<Commande, LocalDateTime> dateCmd;
    public static volatile SingularAttribute<Commande, Long> id;
    public static volatile ListAttribute<Commande, CommandeItem> commandeItems;
    public static volatile ListAttribute<Commande, User> users;

}