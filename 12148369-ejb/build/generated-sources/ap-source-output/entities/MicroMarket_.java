package entities;

import entities.Customer;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-27T23:38:40")
@StaticMetamodel(MicroMarket.class)
public class MicroMarket_ { 

    public static volatile SingularAttribute<MicroMarket, Double> areaLength;
    public static volatile CollectionAttribute<MicroMarket, Customer> customerCollection;
    public static volatile SingularAttribute<MicroMarket, String> zipCode;
    public static volatile SingularAttribute<MicroMarket, Double> radius;
    public static volatile SingularAttribute<MicroMarket, Double> areaWidth;

}