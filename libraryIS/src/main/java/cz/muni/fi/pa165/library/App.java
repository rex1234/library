package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.daos.BookDao;
import cz.muni.fi.pa165.library.daos.LoanDao;
import cz.muni.fi.pa165.library.daos.LoanDaoImpl;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Customer;
import cz.muni.fi.pa165.library.entities.Department;
import cz.muni.fi.pa165.library.entities.Impression;
import cz.muni.fi.pa165.library.entities.Loan;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.joda.time.LocalDate;

public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryTestPU");
        LoanDao dao = new LoanDaoImpl(emf);
        dao.createLoan(getTestLoan());
        
    }
    private static Loan getTestLoan() {
        return createTestLoan(getTestCustomer() , getTestBooks(), new LocalDate(2012,1,1), new LocalDate(2013,1,1));
    }
   
    private static Loan createTestLoan(Customer customer,List<Book> books , LocalDate fromDate, LocalDate toDate ) {
        Loan loan = new Loan();
        loan.setCustomer(customer);
        loan.setFrom(fromDate);
        loan.setTo(toDate);
        loan.setBooks(books);
        return loan;
    }
   
    private static Customer getTestCustomer() {
        Customer cust = new Customer();
        cust.setName("Jano Mjartan");
        cust.setAddress("Kraskova 48");
        cust.setLoans(null);
        return cust;
    }
   
    private static Customer getTestCustomer2() {
        Customer cust = new Customer();
        cust.setName("Michal Kely");
        cust.setAddress("Modra 48");
        cust.setLoans(null);
        return cust;
    }
    
    private static List<Book> getTestBooks() {
        List<Book> books = new ArrayList<Book>();
        Book book1 = new Book();
        Book book2 = new Book();
        book1.setImpression(getTestImpression());
        book1.setCondition("Good");
        book1.setPrinted(new LocalDate(2001,11,8));
        book2.setImpression(null);
        book2.setCondition("Bad");
        book2.setPrinted(new LocalDate(20012,11,8));
        books.add(book2);
        books.add(book1);
        return books;
        
       
        
    }
    
    private static Impression getTestImpression() {
        Impression impression = new Impression();
        impression.setAuthor("Daniel Hevier");
        impression.setName("Pekna kniha");
        impression.setIsbn("123456");
        impression.setDepartment(Department.CHILDREN);
        impression.setRelaseDate(new LocalDate(2000,10,15));
        return impression;
    }
}
