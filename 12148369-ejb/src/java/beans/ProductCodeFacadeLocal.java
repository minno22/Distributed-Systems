/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.ProductCode;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Niall
 */
@Local
public interface ProductCodeFacadeLocal {

    void create(ProductCode productCode);

    void edit(ProductCode productCode);

    void remove(ProductCode productCode);

    ProductCode find(Object id);

    List<ProductCode> findAll();

    List<ProductCode> findRange(int[] range);

    int count();
    
}
