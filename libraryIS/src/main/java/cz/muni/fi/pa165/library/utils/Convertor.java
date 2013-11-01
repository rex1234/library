package cz.muni.fi.pa165.library.utils;

import cz.muni.fi.pa165.library.dtos.BookTO;
import cz.muni.fi.pa165.library.dtos.CustomerTO;
import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import cz.muni.fi.pa165.library.dtos.LoanTO;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Customer;
import cz.muni.fi.pa165.library.entities.Impression;
import cz.muni.fi.pa165.library.entities.Loan;
import java.util.LinkedList;
import java.util.List;

public class Convertor {
    public static BookTO convert(Book book) {
        BookTO result = new BookTO();
        result.setId(book.getId());
        result.setCondition(book.getCondition());
        result.setPrinted(book.getPrinted());
        result.setImpressionTO(Convertor.convert(book.getImpression()));
        result.setLoanTO(Convertor.convert(book.getLoan()));                
        return result;
    }    
    
    public static Book convert(BookTO book) {
        Book result = new Book();
        result.setId(book.getId());
        result.setCondition(book.getCondition());
        result.setPrinted(book.getPrinted());
        result.setImpression(Convertor.convert(book.getImpressionTO()));
        result.setLoan(Convertor.convert(book.getLoanTO()));                
        return result;
    }
    
    public static LoanTO convert(Loan loan) {
        LoanTO result = new LoanTO();
        result.setId(loan.getId());
        result.setToDate(loan.getTo());
        result.setFromDate(loan.getFrom());
        result.setCustomerTO(Convertor.convertCustomerEntityToTO(loan.getCustomer()));
        List<BookTO> bookList = new LinkedList<BookTO>();
        for (Book book : loan.getBooks()) {
            bookList.add(Convertor.convert(book));
        }
        result.setBooks(bookList);
        return result;
    }
    
    public static Loan convert(LoanTO loan) {
        Loan result = new Loan();
        result.setId(loan.getId());
        result.setTo(loan.getToDate());
        result.setFrom(loan.getFromDate());
        result.setCustomer(Convertor.convertCustomerTOToEntity(loan.getCustomerTO()));
        List<Book> bookList = new LinkedList<Book>();
        for (BookTO book : loan.getBooks()) {
            bookList.add(Convertor.convert(book));
        }
        result.setBooks(bookList);
        return result;
    }
    
    public static CustomerTO convertCustomerEntityToTO(Customer customer) {
        CustomerTO result = new CustomerTO();
        result.setId(customer.getId());
        result.setName(customer.getName());
        result.setAddress(customer.getAddress());
        List<LoanTO> customerList = new LinkedList<LoanTO>();
        for (Loan loan : customer.getLoans()) {
            customerList.add(Convertor.convert(loan));
        }
        result.setLoans(customerList);       
        return result;
    }
    
    public static Customer convertCustomerTOToEntity(CustomerTO customer) {
        Customer result = new Customer();
        result.setId(customer.getId());
        result.setName(customer.getName());
        result.setAddress(customer.getAddress());
        List<Loan> customerList = new LinkedList<Loan>();
        for (LoanTO loan : customer.getLoans()) {
            customerList.add(Convertor.convert(loan));
        }
        result.setLoans(customerList);       
        return result;
    }
    
    public static ImpressionTO convert(Impression impression) {
        ImpressionTO result = new ImpressionTO();
        result.setId(impression.getId());
        result.setName(impression.getName());
        result.setIsbn(impression.getIsbn());
        result.setAuthor(impression.getAuthor());
        result.setRelaseDate(impression.getRelaseDate());
        result.setDepartment(impression.getDepartment());
        return result;
    }
    
    public static Impression convert(ImpressionTO impression) {
        Impression result = new Impression();
        result.setId(impression.getId());
        result.setName(impression.getName());
        result.setIsbn(impression.getIsbn());
        result.setAuthor(impression.getAuthor());
        result.setRelaseDate(impression.getRelaseDate());
        result.setDepartment(impression.getDepartment());
        return result;
    }
      
}
