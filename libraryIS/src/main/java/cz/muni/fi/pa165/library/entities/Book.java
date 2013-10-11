package cz.muni.fi.pa165.library.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.joda.time.LocalDate;

/**
 *
 * @author Mjartan
 */

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate printed; 
    @ManyToOne
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
    
    
    
}
