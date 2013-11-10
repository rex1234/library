/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.daos.LoanDao;
import cz.muni.fi.pa165.library.dtos.LoanTO;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Customer;
import cz.muni.fi.pa165.library.entities.Loan;
import cz.muni.fi.pa165.library.services.LoanServiceImpl;
import cz.muni.fi.pa165.library.utils.Convertor;
import java.util.ArrayList;
import static org.mockito.Mockito.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author MiškoHu
 */
public class LoanServiceImplTest {

//    private ApplicationContext context;
//    private LoanDao loanDao;
//    private Map<Long, Loan> loanMap;
//    private Book book1;
//    private Book book2;
//    private Book book3;
//    private Customer customer1;
//    Loan loan1;
//    Loan loan2;
//
//    public LoanServiceImplTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//        context = new ClassPathXmlApplicationContext("applicationTestContext.xml");
//
//        book1 = new Book();
//        book1.setId(1L);
//        book1.setCondition("Zachovala");
//        book1.setPrinted(new LocalDate(2009, 8, 22));
//        book1.setLoan(loan1);
//
//        book2 = new Book();
//        book2.setId(2L);
//        book2.setCondition("Roztrhana");
//        book2.setPrinted(new LocalDate(1998, 4, 13));
//        book2.setLoan(loan2);
//
//        book3 = new Book();
//        book3.setId(3L);
//        book3.setCondition("Nova");
//        book3.setPrinted(new LocalDate(2012, 6, 29));
//        book3.setLoan(loan2);
//
//        customer1 = new Customer();
//        customer1.setId(1L);
//        customer1.setAddress("A.Stodolu 15, Zilina");
//        customer1.setName("Franta Mrazek");
//
//        loan1 = new Loan();
//        loan1.setId(1L);
//        loan1.setFrom(new LocalDate(2013, 9, 11));
//        loan1.setTo(new LocalDate(2013, 10, 12));
//        loan1.setCustomer(customer1);
//        List<Book> books1 = new ArrayList();
//        books1.add(book1);
//        loan1.setBooks(books1);
//
//        loan2 = new Loan();
//        loan2.setId(2L);
//        loan2.setFrom(new LocalDate(2011, 4, 5));
//        loan2.setTo(new LocalDate(2011, 5, 6));
//        loan2.setCustomer(customer1);
//        List<Book> books2 = new ArrayList();
//        books2.add(book2);
//        books2.add(book3);
//        loan2.setBooks(books2);
//
//        List<Loan> loans = new ArrayList();
//        loans.add(loan1);
//        loans.add(loan2);
//        customer1.setLoans(loans);
//
//        loanMap = new HashMap<Long, Loan>();
//
//        loanDao = mock(LoanDao.class);
//        mockUpdate();
//    }
//
//    private void mockUpdate() {
//        doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) {
//                loanMap.put(loan1.getId(), loan1);
//                return null;
//            }
//        }).when(loanDao).createLoan(loan1);
//        doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) {
//                loanMap.put(loan2.getId(), loan2);
//                return null;
//            }
//        }).when(loanDao).createLoan(loan2);
//        doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) {
//                loanMap.put(loan1.getId(), loan1);
//                return null;
//            }
//        }).when(loanDao).updateLoan(loan1);
//        doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) {
//                loanMap.put(loan2.getId(), loan2);
//                return null;
//            }
//        }).when(loanDao).updateLoan(loan2);
//        doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) {
//                loanMap.remove(loan1.getId());
//                return null;
//            }
//        }).when(loanDao).deleteLoan(loan1);
//        doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) {
//                loanMap.remove(loan2.getId());
//                return null;
//            }
//        }).when(loanDao).deleteLoan(loan2);
//
//        when(loanDao.findLoanById(loan1.getId())).thenReturn(loan1);
//        when(loanDao.findLoanById(loan2.getId())).thenReturn(loan2);
//
//        when(loanDao.findAllLoans()).thenReturn(new ArrayList<Loan>(loanMap.values()));
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    @Test
//    public void testCreateLoan() {
//        LoanServiceImpl service = context.getBean(LoanServiceImpl.class);
//        
//
//        LoanTO loan1TO = Convertor.convert(loan1);
//
//        mockUpdate();
//        Integer sizeBefore = service.findAllLoans().size();
//        service.createLoan(loan1TO);
//
//        assertNotNull("Loan ID should be set", loan1TO.getId());
//
//        mockUpdate();
//        List<LoanTO> currentLoans = service.findAllLoans();
//        assertEquals("Only one new loan stored", sizeBefore + 1, currentLoans.size());
//
//        LoanTO insertedLoan = service.findLoanById(loan1TO.getId());
//
//        assertEquals(loan1TO, insertedLoan);
//
//        assertEquals(loan1TO.getId(), insertedLoan.getId());
//        assertEquals(loan1TO.getFromDate(), insertedLoan.getFromDate());
//        assertEquals(loan1TO.getToDate(), insertedLoan.getToDate());
//        assertEquals(loan1TO.getBooks(), insertedLoan.getBooks());
//    }
//
//    @Test
//    public void testUpdateLoan() {
//        LoanServiceImpl service = context.getBean(LoanServiceImpl.class);
//        
//
//        LoanTO loan1TO = Convertor.convert(loan1);
//
//        service.createLoan(loan1TO);
//
//        loan1TO.setFromDate(new LocalDate(2005, 4, 13));
//        loan1TO.setToDate(new LocalDate(2005, 4, 13));
//
//        loan1 = Convertor.convert(loan1TO);
//        mockUpdate();
//        service.updateLoan(loan1TO);
//
//        mockUpdate();
//        LoanTO insertedLoan = service.findLoanById(loan1TO.getId());
//
//        assertEquals(loan1TO, insertedLoan);
//
//        assertEquals(loan1TO.getId(), insertedLoan.getId());
//        assertEquals(loan1TO.getBooks(), insertedLoan.getBooks());
//        assertEquals(loan1TO.getFromDate(), insertedLoan.getFromDate());
//        assertEquals(loan1TO.getToDate(), insertedLoan.getToDate());
//    }
//
//    @Test
//    public void testDeleteVisit() {
//        LoanServiceImpl service = context.getBean(LoanServiceImpl.class);
//        
//
//        LoanTO loan1TO = Convertor.convert(loan1);
//
//        mockUpdate();
//        List<LoanTO> beforeLoans = service.findAllLoans();
//
//        mockUpdate();
//        service.createLoan(loan1TO);
//
//        mockUpdate();
//        service.deleteLoan(loan1TO);
//
//        mockUpdate();
//        List<LoanTO> currentLoans = service.findAllLoans();
//
//        assertEquals(beforeLoans.size(), currentLoans.size());
//
//        boolean oldLoansRemains = true;
//
//        for (LoanTO loanPresent : beforeLoans) {
//            if (!currentLoans.contains(loanPresent)) {
//                oldLoansRemains = false;
//                break;
//            }
//        }
//
//        assertTrue(oldLoansRemains);
//
//        assertFalse(currentLoans.contains(loan1TO));
//    }
//
//    @Test
//    public void testFindAllImpressions() {
//
//        LoanServiceImpl service = context.getBean(LoanServiceImpl.class);
//
//        LoanTO loan1TO = Convertor.convert(loan1);
//        LoanTO loan2TO = Convertor.convert(loan2);
//
//        service.createLoan(loan1TO);
//        service.createLoan(loan2TO);
//        mockUpdate();
//
//        List<LoanTO> loanTOs = service.findAllLoans();
//        assertEquals(2, loanTOs.size());
//        assertEquals(loan1TO, loanTOs.get(0));
//        assertEquals(loan2TO, loanTOs.get(1));
//    }
}
