package cz.muni.fi.pa165.library.utils;

import cz.muni.fi.pa165.library.dtos.BookTO;
import cz.muni.fi.pa165.library.dtos.CustomerTO;
import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import cz.muni.fi.pa165.library.dtos.LoanTO;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Customer;
import cz.muni.fi.pa165.library.entities.Impression;
import cz.muni.fi.pa165.library.entities.Loan;

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
        return null;
    }
    
    public static Loan convert(LoanTO loan) {
        return null;
    }
    
    public static CustomerTO convert(Customer customer) {
        return null;
    }
    
    public static Customer convert(CustomerTO customer) {
        return null;
    }
    
    public static ImpressionTO convert(Impression impression) {
        return null;
    }
    
    public static Impression convert(ImpressionTO impression) {
        return null;
    }
    
}
