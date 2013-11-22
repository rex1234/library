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
    
    private String vajce = "Vajce";
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"create", "save"}, field = "isbn", required = true),
        @Validate(on = {"create", "save"}, field = "author", required = true),
        @Validate(on = {"create", "save"}, field = "name", required = true),
        @Validate(on = {"create", "save"}, field = "releaseDate", required = true, converter = DateConverter.class),})    
    private List<ImpressionTO> availableImpressions;
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
        availableImpressions = imService.findAllImpressions();
        return new RedirectResolution("/loan/available.jsp");
    }

    /*public Resolution create() {
        
     return new RedirectResolution(getClass(), "printImpressions");
     }*/
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "delete"})
    public void loadCustomer() {
        customer = custService.findCustomerById(Long.parseLong(getContext().getRequest().getParameter("customer.id")));
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
        availableImpressions = imService.findNotBorrowedImpressions("");
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

    public String getVajce() {
        return vajce;
    }

    public void setVajce(String vajce) {
        this.vajce = vajce;
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

    public List<ImpressionTO> getAvailableImpressions() {
        return availableImpressions;
    }

    public void setAvailableImpressions(List<ImpressionTO> availableImpressions) {
        this.availableImpressions = availableImpressions;
    }

    public CustomerTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerTO customer) {
        this.customer = customer;
    }

    
}
