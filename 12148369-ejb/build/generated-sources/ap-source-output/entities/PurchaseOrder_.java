package entities;

import entities.Customer;
import entities.Product;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-23T19:39:00")
@StaticMetamodel(PurchaseOrder.class)
public class PurchaseOrder_ { 

    public static volatile SingularAttribute<PurchaseOrder, Customer> customerId;
    public static volatile SingularAttribute<PurchaseOrder, Date> salesDate;
    public static volatile SingularAttribute<PurchaseOrder, Integer> orderNum;
    public static volatile SingularAttribute<PurchaseOrder, BigDecimal> shippingCost;
    public static volatile SingularAttribute<PurchaseOrder, Short> quantity;
    public static volatile SingularAttribute<PurchaseOrder, Date> shippingDate;
    public static volatile SingularAttribute<PurchaseOrder, String> freightCompany;
    public static volatile SingularAttribute<PurchaseOrder, Product> productId;

}