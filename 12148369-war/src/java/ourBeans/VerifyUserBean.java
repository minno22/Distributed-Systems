/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourBeans;

import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import entities.Customer;
import beans.CustomerFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;

/**
 *
 * @author Eoghan
 */
@ManagedBean
@Named(value = "verifyUserBean")
@SessionScoped
public class VerifyUserBean {
    private EntityManager em;
    @EJB private CustomerFacadeLocal newCustomerBean;

    public String username;
    public String password;
    
    public VerifyUserBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    public String setUpDefaultUsers(){
        String returnString = "";
        //SET UP SAMPLE USER IF NOT CREATED
        int userExists = newCustomerBean.addCustomer("joe", "1D10T");
        if(userExists == 1)
            returnString += "JOE sample user already exists in customer table. ";
        else if(userExists == 0)
            returnString += "JOE sample user created in customer table. ";
        //SET UP SAMPLE ADMIN IF NOT CREATED
        userExists = newCustomerBean.addCustomer("toor", "4uldo0!");
        if(userExists == 1)
            returnString += "  \nTOOR sample admin already exists in customer table";
        else if(userExists == 0)
            returnString += "  \nTOOR sample admin created in customer table";
        return returnString;
    }
    
    public String login(){
        int userIdQuery = 0;
        List<Customer> existingCustomer = newCustomerBean.getCustomerByName(username); //GETS CUSTOMER OBJECT
        if(!(existingCustomer.isEmpty())){ //CHECK IF THE CUSTOMER EXISTS FIRST
            if(existingCustomer.equals(newCustomerBean.getCustomerByName(username))) //CHECKS IF THE NAMES MATCH 
            {
               if(username.equals("joe") && password.equals("1D10T?")){
                   getUserInfo();
                   return "Customer/customerMainPage";
               }
               else if(username.equals("toor") && password.equals("4uldo0!"))
                   return "Admin/adminMainPage";
               else return "invalidLogin";
            }
                
            else return "invalidLogin";
        }
        else return "invalidLogin"; 
    }
    
    public void getUserInfo(){
        myProfileBean.setID(newCustomerBean.getCustomerByName(username).get(0).getCustomerId());
    }
}