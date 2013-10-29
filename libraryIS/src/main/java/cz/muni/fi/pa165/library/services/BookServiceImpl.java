package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.daos.BookDao;
import cz.muni.fi.pa165.library.dtos.BookTO;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.utils.Convertor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Mjartan
 */

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    public void createBook(BookTO bookTO) {
        Book bookEntity = Convertor.convert(bookTO);
        bookDao.createBook(bookEntity);
        bookTO.setId(bookEntity.getId());
    }

    public List<BookTO> findAllBooks() {
        List<BookTO> bookTOs = new ArrayList<BookTO>();
        List<Book> bookEntities = bookDao.findAllBooks();
        for (Book book : bookEntities) {
            bookTOs.add(Convertor.convert(book));
        }
        return bookTOs;
    }

    public BookTO findBookById(Long id) {
        return Convertor.convert(bookDao.findBookById(id));
    }

    public void deleteBook(BookTO bookTO) {
        bookDao.deleteBook(Convertor.convert(bookTO));
        bookTO.setId(null);
    }

    public void updateBook(BookTO bookTO) {
        bookDao.updateBook(Convertor.convert(bookTO));
    }
        
}
