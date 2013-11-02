package cz.muni.fi.pa165.library.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;

public class LoanTO {      
    private Long id;    
    private Long customerId;   
    private List<Long> bookIds = new ArrayList<Long>();;   
    private LocalDate fromDate;   
    private LocalDate toDate;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Long> books) {
        this.bookIds = books;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final LoanTO other = (LoanTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LoanTO{" + "id=" + id + ", fromDate=" + fromDate + ", toDate=" + toDate + '}';
    }
    
}
