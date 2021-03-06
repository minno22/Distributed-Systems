/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourBeans;

import beans.CustomerFacadeLocal;
import beans.PurchaseOrderFacadeLocal;
import entities.Customer;
import entities.PurchaseOrder;
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
@Named(value = "customerBean")
@SessionScoped
public class CustomerBean {

    /**
     * Creates a new instance of ProfilesBean
     */
    public CustomerBean() {
    }
    
    @EJB
    CustomerFacadeLocal customerBean;
    
    @EJB public PurchaseOrderFacadeLocal poBean;
    
    public String customerName;
    public int customerID;
    public String customerMessage;
    public String customerPassword;
    
    public List<Customer> showAllProfiles(){
        return customerBean.getAllCustomers();
    }
    
    public String getCustomerName(){
        return customerName;
    }
    
    public void setCustomerName(String s){
        this.customerName = s;
    }
    
    public String getCustomerMessage(){
        return customerMessage;
    }
    
    public void setCustomerMessage(String s){
        this.customerMessage = s;
    }
    
    public String getCustomerPassword(){
        return customerPassword;
    }
    
    public void setCustomerPassword(String s){
        this.customerPassword = s;
    }
    
     public int getCustomerID(){
        return customerID;
    }
    
    public void setCustomerID(int s){
        this.customerID = s;
    }
    
    public List<Customer> getCustomerByName(){
        return customerBean.getCustomerByName(customerName);
    }
    
    public Customer getCustomerByID(){
        return customerBean.getCustomer(customerID);
    }
    
    public void insertCustomer(){
        customerBean.addCustomer(customerName, customerPassword); //add message later
    }
    
    public List<PurchaseOrder> getPOs(){
        return poBean.getAllOrders();
    }
}
