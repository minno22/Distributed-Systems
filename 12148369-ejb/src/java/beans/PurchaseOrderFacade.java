/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Customer;
import entities.DiscountCode;
import entities.Product;
import entities.PurchaseOrder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Niall
 */
@Stateless
public class PurchaseOrderFacade extends AbstractFacade<PurchaseOrder> implements PurchaseOrderFacadeLocal {

    @PersistenceContext(unitName = "12148369-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public PurchaseOrderFacade() {
        super(PurchaseOrder.class);
    }

    @Override
    public void addOrder(int custId, int productId, int quantity) {
        try{
            //Using named queries prevents SQL Injection
            int id = (Integer) em.createNamedQuery("PurchaseOrder.findMaxId").getSingleResult();
            id++;
            PurchaseOrder order = new PurchaseOrder();
            order.setOrderNum(id);
            
            Query query = em.createNamedQuery("Customer.findByCustomerId")
                .setParameter("customerId", "custId");
            
            Customer cust = (Customer) query.getSingleResult();
            order.setCustomerId(cust);
            
            query = em.createNamedQuery("Product.findByProductId")
                .setParameter("productId", "productId");
            
            Product ord = (Product) query.getSingleResult();
            order.setProductId(ord);
            
            short quantity2 = (short)quantity;
            order.setQuantity(quantity2);
                    
            
            persist(order);
            
        }catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    @Override
    public List<PurchaseOrder> getOrder(int orderId) {
        // create named query and set parameter
        Query query = em.createNamedQuery("PurchaseOrder.findByOrderNum")
                .setParameter("orderNum", orderId);
        // return query result
        return query.getResultList();
    }

    @Override
    public List<PurchaseOrder> getAllOrders() {
        // create named query and set parameter
        Query query = em.createNamedQuery("PurchaseOrder.findAll");
        // return query result
        return query.getResultList();
    }
    
}
