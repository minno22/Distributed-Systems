/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niallsPackage;

import entities.Customer;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import beans.CustomerFacadeLocal;
import cart.ShoppingCart;

/**
 *
 * @author Niall
 */

@ManagedBean
@Named(value = "myBean")
@SessionScoped
public class MyBean {
    
    @EJB
    CustomerFacadeLocal helloBean;
    
    @EJB
    ShoppingCart sc;

    public String customerName;
    public String customerCity;
    public String cartItem;
    
    /**
     * Creates a new instance of myBean
     */
    public MyBean() {
    }
    
    public String getCustomerName(){
        return customerName;
    }
    
    public void setCustomerName(String s){
        this.customerName = s;
    }
    
    public String getCustomerCity(){
        return customerCity;
    }
    
    public void setCustomerCity(String s){
        this.customerCity = s;
    }
    
    public String getCartItem(){
        return cartItem;
    }
    
    public void setCartItem(String s){
        this.cartItem = s;
    }
    
    public List<Customer> getCustomerByCity2(){
        return helloBean.getCustomerByCity(customerCity);
    }
    
    public List<Customer> getCustomerByName(){
        return helloBean.getCustomerByName(customerName);
    }
    
    public void insertCustomer(){
        helloBean.addCustomer(customerName, customerCity);
    }
    
    public String testCart(){
        return sc.getItemList();
    }
    
    public void addToCart(){
        sc.addItem(cartItem, 1);
    }
}
