package nl.gridshore.samples.books.business;

import nl.gridshore.samples.books.domain.Book;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Mar 18, 2008
 * Time: 10:25:01 AM
 * Manager class for all book related functionality
 */
public interface BookManager {
    List<Book> listAllBooks();
}
