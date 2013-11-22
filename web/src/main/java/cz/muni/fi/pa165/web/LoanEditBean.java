/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.web;

import cz.muni.fi.pa165.library.dtos.BookTO;
import cz.muni.fi.pa165.library.dtos.CustomerTO;
import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import cz.muni.fi.pa165.library.dtos.LoanTO;
import cz.muni.fi.pa165.library.services.BookService;
import cz.muni.fi.pa165.library.services.CustomerService;
import cz.muni.fi.pa165.library.services.ImpressionService;
import cz.muni.fi.pa165.library.services.LoanService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.joda.time.LocalDate;

/**
 *
 * @author MiškoHu
 */
public class LoanEditBean extends BaseBean implements ValidationErrorHandler {

    @SpringBean
    private ImpressionService imService;
    @SpringBean
    private LoanService loanService;
    @SpringBean
    private CustomerService custService;
    @SpringBean
    private BookService bookService;
    private List<ImpressionTO> impressions;
    private CustomerTO customer;

    /*@DefaultHandler
     public Resolution printImpressions() {
     allImpressions = imService.findAllImpressions();
     return new ForwardResolution("/impression/main.jsp");
     }

     public Resolution listBooks() {
     allImpressions = imService.findAllImpressions();
     ImpressionTO tempImpression =
     imService.findImpressionById(Long.parseLong(getContext().getRequest().getParameter("impression.id")));
     impressionBooks = bookService.findBooksForImpression(tempImpression);
     return new ForwardResolution("/impression/main.jsp");
     }*/
    @DefaultHandler
    public Resolution displayAvailable() {
        customer = custService.findCustomerById(Long.parseLong(getContext().getRequest().getParameter("customer.id")));
        impressions = imService.findAllImpressions();
        return new ForwardResolution("/loan/available.jsp");
    }

    public Resolution create() {
        ImpressionTO impression = imService.findImpressionById(Long.parseLong(getContext().getRequest().getParameter("impression.id")));
        customer = custService.findCustomerById(Long.parseLong(getContext().getRequest().getParameter("customer.id")));
        BookTO b = bookService.findNotBorrowedBookForImpression(impression);
        LoanTO loan = new LoanTO();
        loan.setBook(b);
        loan.setCustomer(customer);
        loan.setFromDate(new LocalDate());
        loanService.createLoan(loan);
        return new ForwardResolution(getClass(), "/index.jsp");
    }

   
    /*public Resolution edit() {
     return new ForwardResolution("/impression/edit.jsp");
     }
     public Resolution save() {
     imService.updateImpression(impression);
     return new RedirectResolution(getClass(), "printImpressions");
     }
     public Resolution delete() {
     imService.deleteImpression(impression);
     return new ForwardResolution(getClass(), "printImpressions");
     }*/
    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) {
        impressions = imService.findNotBorrowedImpressions("");
        return null;
    }

    public ImpressionService getImService() {
        return imService;
    }

    public void setImService(ImpressionService imService) {
        this.imService = imService;
    }

    public LoanService getLoanService() {
        return loanService;
    }

    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

    public CustomerService getCustService() {
        return custService;
    }

    public void setCustService(CustomerService custService) {
        this.custService = custService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public List<ImpressionTO> getImpressions() {
        return impressions;
    }

    public void setImpressions(List<ImpressionTO> impressions) {
        this.impressions = impressions;
    }

    public CustomerTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerTO customer) {
        this.customer = customer;
    }
}
