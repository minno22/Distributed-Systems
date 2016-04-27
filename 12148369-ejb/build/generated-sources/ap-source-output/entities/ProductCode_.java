package entities;

import entities.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-27T23:38:40")
@StaticMetamodel(ProductCode.class)
public class ProductCode_ { 

    public static volatile SingularAttribute<ProductCode, String> description;
    public static volatile SingularAttribute<ProductCode, String> prodCode;
    public static volatile CollectionAttribute<ProductCode, Product> productCollection;
    public static volatile SingularAttribute<ProductCode, Character> discountCode;

}