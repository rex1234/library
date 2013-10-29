package cz.muni.fi.pa165.library.dtos;

import java.io.Serializable;
import org.joda.time.LocalDate;

/**
 * Class representing a book transfer object
 * 
 * @author Mjartan
 */

public class BookTO implements Serializable{
    private Long id;
    private LocalDate printed; 
    private ImpressionTO impression;
    private LoanTO loan;   
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

    public ImpressionTO getImpressionTO() {
        return impression;
    }

    public void setImpressionTO(ImpressionTO impression) {
        this.impression = impression;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public LoanTO getLoanTO() {
        return loan;
    }

    public void setLoanTO(LoanTO loan) {
        this.loan = loan;
    }        

    @Override
    public String toString() {
        return "BookTO{" + "id=" + id + ", printed=" + printed + ", impression=" + impression + ", condition=" + condition + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }    

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof BookTO)) {
            return false;
        }
        if(id == null) {
            return false;
        }
        return ((BookTO)obj).getId().equals(id);
    }    
}
