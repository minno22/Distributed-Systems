/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourBeans;

import beans.CustomerFacadeLocal;
import entities.Customer;
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
@Named(value = "myProfileBean")
@SessionScoped
public class myProfileBean {

    public static int customerID;
    public String userType;
    public String customerMessage;
    
    @EJB
    CustomerFacadeLocal customerBean;
    
    /**
     * Creates a new instance of myProfileBean
     */
    public myProfileBean() {
    }
    
    public int getCustomerID(){
        return customerID;
    }
    
   /* public void setCustomerID(int s){
        this.customerID = s;
    }*/
    
    public String getUserType(){
        return userType;
    }
    
    public void setUserType(String s){
        this.userType = s;
    }
    
    public String getCustomerMessage(){
        return customerMessage;
    }
    
    public void setCustomerMessage(String s){
        this.customerMessage = s;
    }
    
    public void editAccount(){
        Customer cust = customerBean.getCustomer(customerID);
        customerBean.updateAccount(customerID, cust.getName(), cust.getCity(), customerMessage);
    }
    
    public static void setID(int id){
        customerID = id;
    }
    
     public static int getID(){
        return customerID;
    }
}
