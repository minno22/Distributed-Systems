/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourBeans;

import cart.ShoppingCart;
import entities.Product;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
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
        if (checkAvailability()){
            sc.addItem(item, quant);
        }
    }
    
    public boolean checkAvailability(){
        //List<Product> allProds = productBean.getAll();
        //for (int i = 0; i < allProds.size(); i++)
            //if(allProds.get(i).getDescription() == cartItem && allProds.get(i).getQuantityOnHand() >= quantity)
                return true;
        //return false;
    }
    
    public void removeItem(){
        sc.removeItem(cartItem, quantity);
    }
    
    public void removeItem2(String item, int quant){
        sc.removeItem(item, quant);
    }
    
    public void checkout(){
        sc.checkout();
        //reduce quantity of product on hand
    }
    
    public void cancel(){
        sc.cancel();
    }
    
    public List<Product> displayAllItems(){
        List<String> inCart = sc.getAllItems();
        //List<Product> allProds = productBean.getAll();
        List<Product> toReturn = new ArrayList<Product>();
        //for (int i = 0; i < inCart.size(); i++)
            //for (int j = 0; j < allProds.size(); j++)
                //if(allProds.get(j).getDescription() == inCart.get(i).getDescription())
                    //toReturn.add(allProds.get(j));
        return toReturn;
    }
    
    public List<ShoppingCartItemObject> displayCart(){
        List<String> inCart = sc.getAllItems();
        List<Integer> inCartQs = sc.getAllAmounts();
        List<ShoppingCartItemObject> toReturn = new ArrayList<ShoppingCartItemObject>();
        for (int i = 0; i < inCart.size(); i++){
            toReturn.add(new ShoppingCartItemObject((int)inCartQs.get(i), inCart.get(i)));
        }
        return toReturn;
    }
    
    public String viewCart(){
        return sc.getItemList();
    }
}
