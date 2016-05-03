/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourBeans;

import beans.ProductFacadeLocal;
import beans.PurchaseOrderFacadeLocal;
import cart.ShoppingCart;
import entities.Product;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.NoSuchEJBException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Niall
 */
@ManagedBean
@Named(value = "shoppingCartBean")
@SessionScoped
public class ShoppingCartBean {

    @EJB
    ShoppingCart sc;
    
    @EJB private ProductFacadeLocal newProductBean;
    
    @EJB public PurchaseOrderFacadeLocal poBean;
    
    public String cartItem;
    public int quantity;
    
    /**
     * Creates a new instance of ShoppingCartBean
     */
    public ShoppingCartBean() {
    }
 
    public String getCartItem(){
        return cartItem;
    }
    
    public void setCartItem(String s){
        this.cartItem = s;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setQuantity(int s){
        this.quantity = s;
    }
    
    public String displayCartString(){
        return sc.getItemList();
    }
    
    public void addToCart(){
        if (checkAvailability()){
            sc.addItem(cartItem, quantity);
        }
    }
    
    public void addToCart2(String item, int quant){
        cartItem = item;
        quantity = quant;
        if (checkAvailability()){
            sc.addItem(cartItem, quantity);
        }
        else
            System.out.print("DEBUG: Insufficient stock");//should display error message to user here
    }
    
    public boolean checkAvailability(){
        List<Product> allProds = newProductBean.getAllProducts();
        for (int i = 0; i < allProds.size(); i++)
           if((allProds.get(i).getDescription().equals(cartItem)) && (allProds.get(i).getQuantityOnHand() >= quantity))
                return true;
        return false;
    }
    
    public void removeItem(){
        sc.removeItem(cartItem, quantity);
    }
    
    public void removeItem2(String item, int quant){
        sc.removeItem(item, quant);
    }
    
    public void checkout(){
        //reduce quantity of product on hand
        List<ShoppingCartItemObject> items = displayCart();
        for (int i = 0; i < items.size(); i++){
            decreaseQuantity(items.get(i).item, items.get(i).quantity);
            poBean.addOrder(0, myProfileBean.getID(), quantity);
        }
        
      /*  LoggerBean lb = new LoggerBean();
        lb.setMessage("Checking out");
        lb.sendMessage();*/
        
        sc.checkout();
    }
    
    public void cancel(){
        sc.cancel();
    }
    
    public List<ShoppingCartItemObject> displayCart(){
        try{
            List<String> inCart = sc.getAllItems();
            List<Integer> inCartQs = sc.getAllAmounts();
            List<ShoppingCartItemObject> toReturn = new ArrayList<ShoppingCartItemObject>();
            for (int i = 0; i < inCart.size(); i++){
               toReturn.add(new ShoppingCartItemObject((int)inCartQs.get(i), inCart.get(i)));
            }
            return toReturn;
        }
        catch(NoSuchEJBException e){
            System.out.println("DEBUG " + e);
        }
        return null;
    }
    
    public String viewCart(){
        return sc.getItemList();
    }
    
    public void decreaseQuantity(String desc, int amount){
        Product p = newProductBean.getProduct(desc).get(0);
        int id = p.getProductId();
        int quantityOnHand = p.getQuantityOnHand();
        int newQuantity = quantityOnHand - amount;
        newProductBean.updateProduct(id, p.getDescription(), p.getPurchaseCost().intValue(), newQuantity);
    }
}
