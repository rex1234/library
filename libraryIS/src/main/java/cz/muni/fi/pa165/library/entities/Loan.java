package cz.muni.fi.pa165.library.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.joda.time.LocalDate;

/**
 *
 * @author Matej
 */
@Entity
@Table(name = "loan")
public class Loan implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    
    @ManyToOne
    private Customer customer;    
    @OneToMany(mappedBy = "loan")
    private List<Book> books;   
    private LocalDate fromDate; //joda time   
    private LocalDate toDate;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getFrom() {
        return fromDate;
    }

    public void setFrom(LocalDate from) {
        this.fromDate = from;
    }

    public LocalDate getTo() {
        return toDate;
    }

    public void setTo(LocalDate to) {
        this.toDate = to;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
       

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Loan other = (Loan) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}
