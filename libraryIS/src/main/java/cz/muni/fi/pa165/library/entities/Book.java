package cz.muni.fi.pa165.library.entities;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    @ManyToOne(cascade = CascadeType.ALL)
    private Impression impression;
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

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", printed=" + printed + ", impression=" + impression + ", condition=" + condition + '}';
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Book)) {
            return false;
        }
        return ((Book)obj).getId().equals(id);
    }
    
}
