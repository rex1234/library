package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.daos.BookDao;
import cz.muni.fi.pa165.library.daos.ImpressionDao;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Department;
import cz.muni.fi.pa165.library.entities.Impression;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.joda.time.LocalDate;
import org.junit.*;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Mjartan
 */
public class BookDaoImplTest {

    private BookDao dao;
    private Impression im1;
    private Impression im2;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationTestContext.xml");
        dao = context.getBean(BookDao.class);
        ImpressionDao imDao = context.getBean(ImpressionDao.class);

        im1 = getTestImpression();
        im2 = getTestImpression2();
        imDao.createImpression(im2);
        imDao.createImpression(im1);
    }

    @Test
    public void testCreateBook() {

        Book book = getTestBook();
        dao.createBook(book);

        assertNotNull(book.getId());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateNullBook() {

        dao.createBook(null);

    }

    @Test(expected = DataAccessException.class)
    public void testCreateBookWithId() {

        Book book = getTestBook();
        book.setId(5L);
        dao.createBook(book);

    }

    @Test(expected = DataAccessException.class)
    public void testCreateBookWithNullImpression() {

        Book book = getTestBook();
        book.setImpression(null);
        dao.createBook(book);

    }

    @Test(expected = DataAccessException.class)
    public void testCreateBookWithNullDate() {

        Book book = getTestBook();
        book.setPrinted(null);
        dao.createBook(book);

    }

    @Test
    public void testFindAllBooks() {
        List<Book> books = new ArrayList<Book>() {
            {
                add(getTestBook());
                add(createTestBook("Nice", new LocalDate(2008, 3, 25), im1));
                add(createTestBook("Chyba strana 55", new LocalDate(2007, 5, 14), im2));
            }
        };

        for (Book book : books) {

            dao.createBook(book);

        }
        compareBooks(books, dao.findAllBooks());
    }

    @Test
    public void testFindBookById() {
        Book book = getTestBook();

        dao.createBook(book);

        Book found = dao.findBookById(book.getId());
        compareBooks(book, found);
    }

    @Test
    public void testDeleteBook() {

        Book book = getTestBook();
        dao.createBook(book);



        Long oldID = book.getId();
        dao.deleteBook(book);

        assertNull(book.getId());
        assertNull(dao.findBookById(oldID));
    }

    @Test(expected = DataAccessException.class)
    public void testDeletebookWithNullId() {

        Book book = getTestBook();
        dao.createBook(book);


        book.setId(null);

        dao.deleteBook(book);

    }

    @Test
    public void testUpdateBook() {

        Book book = getTestBook();
        dao.createBook(book);

        book.setCondition("Very good");
        book.setPrinted(new LocalDate(2005, 3, 5));
        book.setImpression(im2);

        dao.updateBook(book);

        assertEquals(book, dao.findBookById(book.getId()));
    }

    private Book getTestBook() {
        return createTestBook("Very bad", new LocalDate(2001, 11, 8), im1);
    }

    private Book createTestBook(String condition, LocalDate date, Impression impression) {
        Book book = new Book();
        book.setImpression(impression);
        book.setCondition(condition);
        book.setPrinted(date);
        book.setImpression(impression);
        return book;
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

    private static Impression getTestImpression2() {
        Impression impression = new Impression();
        impression.setAuthor("John Cook");
        impression.setName("Naj recepty");
        impression.setIsbn("98765");
        impression.setDepartment(Department.COOKING);
        impression.setRelaseDate(new LocalDate(1990, 8, 15));
        return impression;
    }

    private void compareBooks(Book b1, Book b2) {
        assertEquals(b1.getCondition(), b2.getCondition());
        assertEquals(b1.getPrinted(), b2.getPrinted());
        assertEquals(b1.getImpression(), b2.getImpression());
        assertEquals(b1.getId(), b2.getId());
    }

    private void compareBooks(List<Book> b1, List<Book> b2) {
        Collections.sort(b1, bookIdCmp);
        Collections.sort(b2, bookIdCmp);
        assertEquals(b1.size(), b2.size());
        for (int i = 0; i < b1.size(); i++) {
            compareBooks(b1.get(i), b2.get(i));
        }
    }
    private static final Comparator<Book> bookIdCmp = new Comparator<Book>() {
        public int compare(Book o1, Book o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };
}
