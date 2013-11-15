package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.daos.BookDaoImpl;
import cz.muni.fi.pa165.library.daos.CustomerDaoImpl;
import cz.muni.fi.pa165.library.daos.ImpressionDaoImpl;
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

    private LoanDaoImpl dao;
    private CustomerDaoImpl custDao;
    private BookDaoImpl bookDao;
    private ImpressionDaoImpl imDao;
    private Impression im1;
    private Book book1;
    private Book book2;
    private Customer cust1;
    private Customer cust2;
    
    private EntityManager em;

    public LoanDaoImplTest() {
    }

    @Before
    public void setUp() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryTestPU");        
        em = emf.createEntityManager();        
        dao = new LoanDaoImpl();
        imDao = new ImpressionDaoImpl();
        custDao = new CustomerDaoImpl();
        bookDao = new BookDaoImpl();
        
        imDao.setEntityManager(em);
        bookDao.setEntityManager(em);
        custDao.setEntityManager(em);
        dao.setEntityManager(em);   

        em.getTransaction().begin();
        im1 = getTestImpression();
        book1 = getTestBook();
        book2 = getTestBook2();
        cust1 = getTestCustomer();
        cust2 = getTestCustomer2();

        imDao.createImpression(im1);
        bookDao.createBook(book1);
        bookDao.createBook(book2);
        custDao.createCustomer(cust1);
        custDao.createCustomer(cust2);
        em.getTransaction().commit();
    }

    @Test
    public void testCreateLoan() {
        Loan loan = getTestLoan();
        em.getTransaction().begin();
        dao.createLoan(loan);
        em.getTransaction().commit();
        Loan loan2 = dao.findLoanById(loan.getId());
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
        
        dao.createLoan(loan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateLoanWithNullFromDate() {
        Loan loan = getTestLoan();
      
        dao.createLoan(loan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateLoanWithNullToDate() {
        Loan loan = getTestLoan();
       
        dao.createLoan(loan);
    }

    @Test
    public void testFindAllLoans() {
        List<Loan> loans = new ArrayList<Loan>() {
            {
                add(getTestLoan());
                add(createTestLoan(cust1, getTestBooks(), new LocalDate(2005, 3, 5), new LocalDate(2005, 12, 12)));
                add(createTestLoan(cust2, getTestBooks(), new LocalDate(1999, 1, 1), new LocalDate(2012, 12, 12)));
            }
        };
        for (Loan loan : loans) {
            em.getTransaction().begin();
            dao.createLoan(loan);
            em.getTransaction().commit();
        }
        compareLoans(loans, dao.findAllLoans());
    }

    
    @Test
    public void testFindLoanById() {
        Loan loan = getTestLoan();
        em.getTransaction().begin();
        dao.createLoan(loan);
        em.getTransaction().commit();
        Loan found = dao.findLoanById(loan.getId());
        compareLoans(loan, found);
    }

    @Test
    public void testDeleteLoan() {
        Loan loan = getTestLoan();
        em.getTransaction().begin();
        dao.createLoan(loan);
        em.getTransaction().commit();
        Long oldID = loan.getId();
        em.getTransaction().begin();
        dao.deleteLoan(loan);
        em.getTransaction().commit();
        assertNull(loan.getId());
        assertNull(dao.findLoanById(oldID));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteLoanWithNullId() {
        Loan loan = getTestLoan();
        em.getTransaction().begin();
        dao.createLoan(loan);
        em.getTransaction().commit();
        loan.setId(null);
        em.getTransaction().begin();
        dao.deleteLoan(loan);
        em.getTransaction().commit();
    }

    @Test
    public void testUpdateBook() {
        Loan loan = getTestLoan();        
        em.getTransaction().begin();
        dao.createLoan(loan);
        em.getTransaction().commit();
       
        em.getTransaction().begin();
        dao.updateLoan(loan);
        em.getTransaction().commit();
        assertEquals(loan, dao.findLoanById(loan.getId()));
    }

    private Loan getTestLoan() {
        return createTestLoan(cust1, getTestBooks(), new LocalDate(2012, 1, 1), new LocalDate(2013, 1, 1));
    }

    private Loan createTestLoan(Customer customer, List<Book> books, LocalDate fromDate, LocalDate toDate) {
        Loan loan = new Loan();
        loan.setCustomer(customer);
        
        return loan;
    }

    private static Customer getTestCustomer() {
        Customer cust = new Customer();
        cust.setName("Jano Mjartan");
        cust.setAddress("Kraskova 48");
      
        return cust;
    }

    private static Customer getTestCustomer2() {
        Customer cust = new Customer();
        cust.setName("Michal Kely");
        cust.setAddress("Modra 48");
      
        return cust;
    }

    private Book getTestBook() {
        Book book = new Book();
        book.setImpression(im1);
        book.setCondition("Good");

        return book;
    }

    private Book getTestBook2() {
        Book book = new Book();
        book.setImpression(im1);
        book.setCondition("Bad");

        return book;
    }

    private List<Book> getTestBooks() {
        List<Book> books = new ArrayList<Book>();

        books.add(book1);
        books.add(book2);
        return books;

    }

    private static Impression getTestImpression() {
        Impression impression = new Impression();
        impression.setAuthor("Daniel Hevier");
        impression.setName("Pekna kniha");
        impression.setIsbn("123456");
        impression.setDepartment(Department.CHILDREN);
        impression.setRelaseDate(new LocalDate(2000, 10, 15));
        return impression;
    }

    private void compareLoans(Loan l1, Loan l2) {
        assertEquals(l1.getCustomer(), l2.getCustomer());
       
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