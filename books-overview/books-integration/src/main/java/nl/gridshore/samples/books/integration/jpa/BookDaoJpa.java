package nl.gridshore.samples.books.integration.jpa;

import nl.gridshore.samples.books.integration.BookDao;
import nl.gridshore.samples.books.domain.Book;
import nl.gridshore.samples.books.domain.Author;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Mar 19, 2008
 * Time: 7:51:08 AM
 * Data acces class using JPA technology
 */
public class BookDaoJpa extends BaseDaoJpa<Book> implements BookDao {
    public BookDaoJpa() {
        super(Book.class, "Book");
    }
}
