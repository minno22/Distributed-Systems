/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Product;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Niall
 */
@Local
public interface ProductFacadeLocal {

    void create(Product product);

    void edit(Product product);

    void remove(Product product);

    Product find(Object id);

    List<Product> findAll();

    List<Product> findRange(int[] range);

    int count();
    
    void addProduct(String Description, double cost, int quantity);
    
    public List<Product> getAllProducts();
    
    public void update(int id, String Description, double cost, int quantity);
    
    public Product getProduct(int id);
    
    public List<Product> getProduct(String description);
    
    void removeProduct(String description);
}
