/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.PurchaseOrder;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Niall
 */
@Local
public interface PurchaseOrderFacadeLocal {

    void create(PurchaseOrder purchaseOrder);

    void edit(PurchaseOrder purchaseOrder);

    void remove(PurchaseOrder purchaseOrder);

    PurchaseOrder find(Object id);

    List<PurchaseOrder> findAll();

    List<PurchaseOrder> findRange(int[] range);

    int count();
    
    public void addOrder(int custId, int productId, int quantity);
    
    public List<PurchaseOrder> getOrder(int orderId);
    
    public List<PurchaseOrder> getAllOrders();
    
    public List<PurchaseOrder> getOrdersCustID(int ID);
    
}
