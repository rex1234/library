package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.daos.BookDao;
import cz.muni.fi.pa165.library.daos.BookDaoImpl;
import cz.muni.fi.pa165.library.daos.CustomerDao;
import cz.muni.fi.pa165.library.daos.CustomerDaoImpl;
import cz.muni.fi.pa165.library.daos.LoanDao;
import cz.muni.fi.pa165.library.daos.LoanDaoImpl;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Customer;
import cz.muni.fi.pa165.library.entities.Department;
import cz.muni.fi.pa165.library.entities.Impression;
import cz.muni.fi.pa165.library.entities.Loan;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.joda.time.LocalDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Matej
 */
public class LoanDaoImplTest {
    
    private LoanDao dao;
    private CustomerDao custDao;
    private BookDao bookDao;
   
    public LoanDaoImplTest() {       
    }
   
    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryTestPU");
        dao = new LoanDaoImpl(emf);
        custDao = new CustomerDaoImpl(emf);        
        bookDao = new BookDaoImpl(emf);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Loan").executeUpdate();
        em.getTransaction().commit();
    }
   
   
    @Test
    public void testCreateLoan() {
        Loan loan = getTestLoan();
        custDao.createCustomer(loan.getCustomer());
        for (Book book : loan.getBooks()) {
            bookDao.createBook(book);
        }
        dao.createLoan(loan);
        assertNotNull(loan.getId());
    }
    
    @Test(expected = NullPointerException.class)
    public void testCreateNullLoan() {
        dao.createLoan(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateLoanWithId() {
        Loan loan = getTestLoan();
        loan.setId(5L);
        dao.createLoan(loan);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateLoanWithNullCustomer() {
        Loan loan = getTestLoan();
        loan.setCustomer(null);
        dao.createLoan(loan);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateLoanWithoutBooks() {
        Loan loan = getTestLoan();
        loan.setBooks(null);
        dao.createLoan(loan);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateLoanWithNullFromDate() {
        Loan loan = getTestLoan();
        loan.setFrom(null);
        dao.createLoan(loan);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateLoanWithNullToDate() {
        Loan loan = getTestLoan();
        loan.setTo(null);
        dao.createLoan(loan);
    }
 
    @Test
    public void testFindAllLoans() {
        List<Loan> loans = new ArrayList<Loan>() {{
            add(getTestLoan());
            add(createTestLoan(getTestCustomer(),getTestBooks(),new LocalDate(2005,3,5),new LocalDate(2005,12,12)));
            add(createTestLoan(getTestCustomer2(),getTestBooks(),new LocalDate(1999,1,1),new LocalDate(2012,12,12)));
        }};
        for (Loan loan : loans) {
            dao.createLoan(loan);
        }
        compareLoans(loans, dao.findAllLoans());
    }
 
    @Test
    public void testFindLoanById() {
        Loan loan = getTestLoan();
        dao.createLoan(loan);
        Loan found = dao.findLoanById(loan.getId());
        compareLoans(loan, found);
    }
 
    @Test
    public void testDeleteLoan() {
        Loan loan = getTestLoan();
        dao.createLoan(loan);
        Long oldID = loan.getId();
        dao.deleteLoan(loan);
        assertNull(loan.getId());
        assertNull(dao.findLoanById(oldID));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteLoanWithNullId() {
        Loan loan = getTestLoan();
        dao.createLoan(loan);
        loan.setId(null);
        dao.deleteLoan(loan);
    }
 
    @Test
    public void testUpdateBook() {
        Loan loan = getTestLoan();
        dao.createLoan(loan);
        loan.setFrom(new LocalDate(2004,3,5));
        loan.setTo(new LocalDate(2005,3,5));
        dao.updateLoan(loan);
        assertEquals(loan, dao.findLoanById(loan.getId()));
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
    
    
   
    private void compareLoans(Loan l1, Loan l2) {
        assertEquals(l1.getCustomer(), l2.getCustomer());
        assertEquals(l1.getFrom(), l2.getFrom());
        assertEquals(l1.getTo(), l2.getTo());
        assertEquals(l1.getBooks(), l2.getBooks());
        assertEquals(l1.getId(), l2.getId());
    }
   
    private void compareLoans(List<Loan> l1, List<Loan> l2) {
        Collections.sort(l1, loanIdCmp);
        Collections.sort(l2, loanIdCmp);
        assertEquals(l1.size(), l2.size());
        for (int i = 0; i < l1.size(); i++) {
            compareLoans(l1.get(i), l2.get(i));
        }
    }
   
    private static Comparator<Loan> loanIdCmp = new Comparator<Loan>() {
        public int compare(Loan o1, Loan o2) {
            return o1.getId().compareTo(o2.getId());
        }        
    };
    
}
