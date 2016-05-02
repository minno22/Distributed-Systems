/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Customer;
import entities.DiscountCode;
import entities.MicroMarket;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Niall
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> implements CustomerFacadeLocal{

    @PersistenceContext(unitName = "12148369-ejbPU")
    private EntityManager em;
    
    private String customerName;
    private String customerCity;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public int addCustomer(String name, String password)
    {
        List<Customer> existingCustomer = getCustomerByName(name); //CHECKS IF THERE IS A USER WITH THE SAME NAME IN THE DB
        if(!(existingCustomer.isEmpty()))
          {
            return 1; //USER ALREADY EXISTS IN THE DATABASE SO NO NEED TO CREATE ANOTHER ONE
          }
        else{
          try{
            //Using named queries prevents SQL Injection
            int id = (Integer) em.createNamedQuery("Customer.findMaxId").getSingleResult();
            id++;
                Customer cust = new Customer();
                cust.setCustomerId(id);
                cust.setName(name);
                cust.setCity(password);
            
                Query query = em.createNamedQuery("DiscountCode.findByDiscountCode")
                    .setParameter("discountCode", "N");
            
                DiscountCode dc = (DiscountCode) query.getSingleResult();
                cust.setDiscountCode(dc);
            
                query = em.createNamedQuery("MicroMarket.findByZipCode")
                    .setParameter("zipCode", "95051");
            
                MicroMarket mm = (MicroMarket) query.getSingleResult();
            
                cust.setZip(mm);
            
            
            
                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                Validator validator = factory.getValidator();

                Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(cust);

                if (constraintViolations.size() > 0 ) {
                   System.out.println("Constraint Violations occurred..");
                    for (ConstraintViolation<Customer> contraints : constraintViolations) {
                       System.out.println(contraints.getRootBeanClass().getSimpleName()+
                      "." + contraints.getPropertyPath() + " " + contraints.getMessage());
                   }
                }
                persist(cust);
            
        }catch (Exception e) {
            System.out.println("error " + e);
        }
        return 0;
      }
    }
    
    @Override
    public List<Customer> getCustomerByName(String c) {
        customerName = c;
        // create named query and set parameter
        Query query = em.createNamedQuery("Customer.findByName")
                .setParameter("name", customerName);
        // return query result
        return query.getResultList();
    }

    /**
     * Returns a list of customer from a given city
     * 
     * @return List of customers in a city
     */
    @Override
    public List<Customer> getCustomerByCity(String c) {
        customerCity = c;
        // create named query and set parameter
        Query query = em.createNamedQuery("Customer.findByCity")
                .setParameter("city", customerCity);
        // return query result
        return query.getResultList();
    }
    
    @Override
    public void update(int id, String name, String password, String message) {
        Customer customer = getCustomer(id);
        if (customer != null) {
            customer.setName(name);
            customer.setCity(password);
            customer.setFax(message);
        }
    }

    /**
     * Returns an entity object of customer with given id
     * 
     * @param id ID of customer to be returned
     * @return Customer object with id 
     */
    @Override
    public Customer getCustomer(int id) {
        // create named query and set parameter
        Query query = em.createNamedQuery("Customer.findByCustomerId")
                .setParameter("customerId", id);
        List<Customer> result = query.getResultList();
        return result.get(0);
    }
    
     public void persist(Object object) {
        em.persist(object);
    }
    
    public CustomerFacade() {
        super(Customer.class);
    }

    @Override
    public List<Customer> getAllCustomers() {
         // create named query and set parameter
        Query query = em.createNamedQuery("Customer.findAll");
        // return query result
        return query.getResultList();
    }
    
}
