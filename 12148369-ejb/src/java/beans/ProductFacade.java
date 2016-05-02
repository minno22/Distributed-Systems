/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Manufacturer;
import entities.Product;
import entities.ProductCode;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Niall
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> implements ProductFacadeLocal {

    @PersistenceContext(unitName = "12148369-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    public ProductFacade() {
        super(Product.class);
    }

    @Override
    public void addProduct(String Description, double cost, int quantity) {
        try{
            //Using named queries prevents SQL Injection
            int id = (Integer) em.createNamedQuery("Product.findMaxId").getSingleResult();
            id++;
            Product product = new Product();
            product.setProductId(id);
            product.setPurchaseCost(new BigDecimal(cost, MathContext.DECIMAL64));
            product.setQuantityOnHand(quantity);
            
            
            Query query = em.createNamedQuery("ProductCode.findByProdCode")
                .setParameter("prodCode", "SW");
            
            ProductCode pc = (ProductCode) query.getSingleResult();
            product.setProductCode(pc);
            
            query = em.createNamedQuery("Manufacturer.findByManufacturerId")
                .setParameter("manufacturerId", "19985678");
            
            Manufacturer man = (Manufacturer) query.getSingleResult();
            product.setManufacturerId(man);
            
            persist(product);
        }catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        // create named query and set parameter
        Query query = em.createNamedQuery("Product.findAll");
        // return query result
        return query.getResultList();
    }

    @Override
    public void update(int id, String Description, double cost, int quantity) {
          Product prod = getProduct(id);
        if (prod != null) {
            prod.setQuantityOnHand(quantity);
        }
    }

    @Override
    public Product getProduct(int id) {
        // create named query and set parameter
        Query query = em.createNamedQuery("Product.findByProductId")
                .setParameter("productId", id);
        List<Product> result = query.getResultList();
        return result.get(0);
    }
    
    @Override
    public void removeProduct(String description){
        Query query = em.createNamedQuery("Product.deleteProductFromTable").setParameter("description", description);
    }
}
