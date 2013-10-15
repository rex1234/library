package cz.muni.fi.pa165.library.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import cz.muni.fi.pa165.library.entities.Loan;

/**
 *
 * @author vit.mica
 */

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    @Column(nullable = false)
    private String name;
    
    private String address;
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Loan> loans = new ArrayList<Loan>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
    
    @Override
    public int hashCode() {
        return (this.id != null ? this.id.hashCode() : 0);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (this.id == null || !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
    
    
}
