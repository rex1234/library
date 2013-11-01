/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.library;

import static cz.muni.fi.pa165.library.ImpressionDaoImplTest.getTestImpression;
import static cz.muni.fi.pa165.library.ImpressionServiceImplTest.getTestImpressionTO;
import cz.muni.fi.pa165.library.daos.BookDao;
import cz.muni.fi.pa165.library.daos.ImpressionDao;
import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import cz.muni.fi.pa165.library.entities.Department;
import cz.muni.fi.pa165.library.entities.Impression;
import cz.muni.fi.pa165.library.services.BookService;
import cz.muni.fi.pa165.library.services.ImpressionService;
import cz.muni.fi.pa165.library.services.ImpressionServiceImpl;
import cz.muni.fi.pa165.library.utils.Convertor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static junit.framework.TestCase.assertEquals;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.powermock.modules.junit4.PowerMockRunner;
import cz.muni.fi.pa165.library.BookDaoImplTest;
import cz.muni.fi.pa165.library.dtos.BookTO;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.services.BookServiceImpl;

/**
 *
 * @author MiškoHu
 */
@RunWith(PowerMockRunner.class)
public class BookServiceImplTest {

    @Mock
    BookDao dao;
    @InjectMocks
    private BookService service = new BookServiceImpl();

    @Test
    public void testFindImpression() {
        when(dao.findBookById(5L)).thenReturn(BookDaoImplTest.getTestBook());
        compareBooks(getTestBookTO(), service.findBookById(5L));
    }

    @Test
    public void testFindAllBooks() {
        List<Book> bks = new ArrayList<Book>() {
            {
                add(createTestBook(5L, "Stara"));
                add(createTestBook(10L, "Nova"));
                add(createTestBook(15L, "Roztrhana"));
            }
        };

        ArrayList<BookTO> bookTOs = new ArrayList<BookTO>() {
            {
                add(createTestBookTO(5L, "Stara"));
                add(createTestBookTO(10L, "Nova"));
                add(createTestBookTO(15L, "Roztrhana"));
            }
        };
        when(dao.findAllBooks()).thenReturn(bks);
        compareBooks(bookTOs, service.findAllBooks());
    }

    @Test
    public void testDeleteCustomer() {
        service.deleteBook(getTestBookTO());
        verify(dao).deleteBook(Convertor.convert(getTestBookTO()));
    }

    @Test
    public void testUpdateCustomer() {
        service.updateBook(getTestBookTO());
        verify(dao).updateBook(Convertor.convert(getTestBookTO()));
    }

    static BookTO getTestBookTO() {
        return Convertor.convert(BookDaoImplTest.getTestBook());
    }

    private static void compareBooks(BookTO i1, BookTO i2) {
        assertEquals(i1.getCondition(), i2.getCondition());
        assertEquals(i1.getPrinted(), i2.getPrinted());
        assertEquals(i1.getImpressionTO(), i2.getImpressionTO());
        assertEquals(i1.getLoanTO(), i2.getLoanTO());
    }

    private static Book createTestBook(Long id, String state) {
        Book b = BookDaoImplTest.createTestBook(state, new LocalDate(1999, 11, 6), BookDaoImplTest.getTestImpression());
        b.setId(id);
        return b;
    }

    private static BookTO createTestBookTO(Long id, String condition) {
        return Convertor.convert(createTestBook(id, condition));
    }

    private void compareBooks(List<BookTO> i1, List<BookTO> i2) {
        Collections.sort(i1, bookIdCmp);
        Collections.sort(i2, bookIdCmp);
        assertEquals(i1.size(), i2.size());
        for (int i = 0; i < i1.size(); i++) {
            compareBooks(i1.get(i), i2.get(i));
        }
    }
    private static Comparator<BookTO> bookIdCmp = new Comparator<BookTO>() {
        public int compare(BookTO o1, BookTO o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };
}
