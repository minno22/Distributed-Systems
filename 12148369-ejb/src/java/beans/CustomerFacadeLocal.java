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
public interface CustomerFacadeLocal {

    void create(Customer customer);

    void edit(Customer customer);

    void remove(Customer customer);

    Customer find(Object id);

    List<Customer> findAll();

    List<Customer> findRange(int[] range);

    int count();
    
    int addCustomer(String name, String password);
    
    public List<Customer> getAllCustomers();
    
    public List<Customer> getCustomerByName(String c);
    
    public List<Customer> getCustomerByCity(String c);
    
    public void updateAccount(int id, String name, String city, String state);
    
    public Customer getCustomer(int id);
    
}
