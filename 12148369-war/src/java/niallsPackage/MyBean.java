/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niallsPackage;

import beans.showCustomerBeanLocal;
import entities.Customer;
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
@Named(value = "myBean")
@SessionScoped
public class MyBean {
    @EJB
    showCustomerBeanLocal helloBean;

    public String customerName;
    public String customerCity;
    
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
    
    public void getCustomerByCity2(){
        //return helloBean.getCustomerByCity();
    }
    
}
