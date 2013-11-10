package cz.muni.fi.pa165.library.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.joda.time.LocalDate;

/**
 * Class representing a book entity
 * 
 * @author Mjartan
 */

@Entity
@Table(name = "book")
public class Book implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private LocalDate printed; 
    @ManyToOne
    private Impression impression;
    @ManyToOne (cascade = CascadeType.ALL)
    private Loan loan;
    @Column(name = "bookCondition")
    private String condition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPrinted() {
        return printed;
    }

    public void setPrinted(LocalDate printed) {
        this.printed = printed;
    }

    public Impression getImpression() {
        return impression;
    }

    public void setImpression(Impression impression) {
        this.impression = impression;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }        

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", printed=" + printed + ", impression=" + impression + ", condition=" + condition + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Book)) {
            return false;
        }
        if(id == null) {
            return false;
        }
        return ((Book)obj).getId().equals(id);
    }
    
}
