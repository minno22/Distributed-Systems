/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.ProductCode;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Niall
 */
@Stateless
public class ProductCodeFacade extends AbstractFacade<ProductCode> implements ProductCodeFacadeLocal {

    @PersistenceContext(unitName = "12148369-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductCodeFacade() {
        super(ProductCode.class);
    }
    
}
