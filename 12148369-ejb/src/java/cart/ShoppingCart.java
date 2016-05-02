/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.ejb.Remove;

/**
 *
 * @author Niall
 */
@Stateful
@LocalBean
public class ShoppingCart implements CartInterface{

    private HashMap<String, Integer> items = new HashMap<String, Integer>();
    
    @Override
    public void addItem(String id, int quantity) {
        // obtain current number of items in cart
        Integer orderQuantity = items.get(id);
        if (orderQuantity == null) {
            orderQuantity = 0;
        }
        // adjust quantity and put back to cart
        orderQuantity += quantity;
        items.put(id, orderQuantity);
    }

    @Override
    public void removeItem(String id, int quantity) {
        // obtain current number of items in cart
        Integer orderQuantity = items.get(id);
        if (orderQuantity == null) {
            orderQuantity = 0;
        }
        // adjust quantity and put back to cart
        orderQuantity -= quantity;
        if (orderQuantity <= 0) {
            // final quantity less equal 0 - remove from cart
            items.remove(id);
        } else {
            // final quantity > 0 - adjust quantity
            items.put(id, orderQuantity);
        }
        
    }

    @Override
    @Remove
    public String checkout() {
        // dummy checkout method that just returns message for successful 
        // checkout
        String message = "You checked out the following items:\n<br>" + getItemList();
        return message;
    }

    @Override
    @Remove
    public void cancel() {
        // no action required - annotation @Remove indicates
        // that calling this method should remove the EJB which will
        // automatically destroy instance variables
    }

    @Override
    public String getItemList() { //Only return items which contain one or more entries or else if we had 1000 books and we only wanted one 999 would shouw up with a 0 beside them
        String message = "";
        Set<String> keys = items.keySet();
        Iterator<String> it = keys.iterator();
        String k;
        while (it.hasNext()) {
            k = it.next();
            if(items.get(k) > 0){ //only return items we have actully added to the cart and not 0
            message += k + ", quantity: " + items.get(k) + "\n<br>";
            }
        }
        return message;
    }
    
    //Needed to match the books we are adding and not displaying all
    
    @Override
    public List <String> getAllItems(){
    List <String> booksInCart = new ArrayList<>();
        Set<String> keys = items.keySet();
        Iterator<String> it = keys.iterator();
        String k;
        while (it.hasNext()) {
            k = it.next();
            booksInCart.add(k);
        }
        
       return booksInCart;
    }
    
    @Override
    public List <Integer> getAllAmounts(){
        List <Integer> amountsInCart = new ArrayList<>();
        Set<String> keys = items.keySet();
        Iterator<String> it = keys.iterator();
        String k;
        while (it.hasNext()) {
            k = it.next();
            if(items.get(k) > 0){ //only return items we have actully added to the cart and not 0
             amountsInCart.add(items.get(k));
            }
        }
        
       return amountsInCart;
        
    }

}