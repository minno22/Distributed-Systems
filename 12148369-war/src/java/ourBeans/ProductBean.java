/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourBeans;

import beans.ProductFacadeLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

/**
 *
 * @author Eoghan
 */
@Named(value = "productBean")
@ManagedBean
@SessionScoped
public class ProductBean 
{
    private EntityManager em;
    @EJB private ProductFacadeLocal newProductBean;
    
    private String description;
    private double cost;
    private int quantity;
    
    public ProductBean() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void addNewProduct(){
        newProductBean.addProduct(description, cost, quantity);
    }
    
    public void removeProduct(){
        newProductBean.removeProduct(description);
    }
    
    
}