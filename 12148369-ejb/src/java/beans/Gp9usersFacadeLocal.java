/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Gp9users;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Niall
 */
@Local
public interface Gp9usersFacadeLocal {

    void create(Gp9users gp9users);

    void edit(Gp9users gp9users);

    void remove(Gp9users gp9users);

    Gp9users find(Object id);

    List<Gp9users> findAll();

    List<Gp9users> findRange(int[] range);

    int count();
    
}
