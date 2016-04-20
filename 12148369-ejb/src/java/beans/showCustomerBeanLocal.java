/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Customer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Niall
 */
@Local
public interface showCustomerBeanLocal {
    public List<Customer> getCustomerByName();
    public List<Customer> getCustomerByCity();
    public String getCustomerName();
    public void setCustomerName(String customerName);
    public String getCustomerCity();
    public void setCustomerCity(String customerCity);
    public void persist(Object object);
}
