/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Niall
 */
@Entity
@Table(name = "GP9USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gp9users.findAll", query = "SELECT g FROM Gp9users g"),
    @NamedQuery(name = "Gp9users.findByUserid", query = "SELECT g FROM Gp9users g WHERE g.userid = :userid"),
    @NamedQuery(name = "Gp9users.findByUsertype", query = "SELECT g FROM Gp9users g WHERE g.usertype = :usertype"),
    @NamedQuery(name = "Gp9users.findByCustomerid", query = "SELECT g FROM Gp9users g WHERE g.customerid = :customerid"),
    @NamedQuery(name = "Gp9users.findByPassword", query = "SELECT g FROM Gp9users g WHERE g.password = :password")})
public class Gp9users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERID")
    private Integer userid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERTYPE")
    private int usertype;
    @Column(name = "CUSTOMERID")
    private Integer customerid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PASSWORD")
    private String password;

    public Gp9users() {
    }

    public Gp9users(Integer userid) {
        this.userid = userid;
    }

    public Gp9users(Integer userid, int usertype, String password) {
        this.userid = userid;
        this.usertype = usertype;
        this.password = password;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gp9users)) {
            return false;
        }
        Gp9users other = (Gp9users) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Gp9users[ userid=" + userid + " ]";
    }
    
}
