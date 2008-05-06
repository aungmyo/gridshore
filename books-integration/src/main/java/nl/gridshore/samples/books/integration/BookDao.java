package nl.gridshore.samples.books.integration;

import nl.gridshore.samples.books.domain.Book;
import nl.gridshore.samples.books.common.vo.BookSearchRequest;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Mar 18, 2008
 * Time: 1:19:15 PM
 * Specific Data access class for books
 */
public interface BookDao extends BaseDao<Book>{
    List<Book> loadByFilter(BookSearchRequest searchRequest);
}
