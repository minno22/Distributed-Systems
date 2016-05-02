/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourBeans;

/**
 *
 * @author Niall
 */
public class ShoppingCartItemObject {
    public int quantity;
    public String item;
    
    public ShoppingCartItemObject(int q, String item){
        this.quantity = q;
        this.item = item;
    }
    
    public void setQuantity(int num){
        this.quantity = num;
    }
    
    public int getQuantity(){
        return this.quantity;
    }
    
    public void setItem(String item){
        this.item = item;
    }
    
    public String getItem(){
        return this.item;
    }
}
