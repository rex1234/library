package cz.muni.fi.pa165.library.daos;

import cz.muni.fi.pa165.library.entities.Book;
import java.util.List;

/**
 *
 * @author Mjartan
 */
public interface BookDao {
    void createBook(Book book);
    List<Book> findAllBooks();
    Book findBookById(Long id);
    void deleteBook(Book book);
    void updateBook(Book book);    
}
