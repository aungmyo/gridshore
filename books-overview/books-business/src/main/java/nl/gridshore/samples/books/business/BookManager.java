package nl.gridshore.samples.books.business;

import nl.gridshore.samples.books.domain.Book;
import nl.gridshore.samples.books.common.vo.BookSearchRequest;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Mar 18, 2008
 * Time: 10:25:01 AM
 * Manager class for all book related functionality
 */
public interface BookManager {
    List<Book> obtainAllBooks();
    List<Book> obtainFilteredBooks(BookSearchRequest searchRequest);
    void storeBook(Book book);

    /**
     * This method is used to prefill the database, this is not a good practice in real applications.
     * We need this to be able to demonstrate the security requirements.
     * @param book Book to store in the database
     */
    void internalUseStoreBook(Book book);
}
