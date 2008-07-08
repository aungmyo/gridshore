package nl.gridshore.samples.books.business.impl;

import nl.gridshore.samples.books.business.BookManager;
import nl.gridshore.samples.books.integration.BookDao;
import nl.gridshore.samples.books.integration.AuthorDao;
import nl.gridshore.samples.books.domain.Book;
import nl.gridshore.samples.books.domain.Author;
import nl.gridshore.samples.books.common.vo.BookSearchRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Mar 19, 2008
 * Time: 7:51:00 PM
 * Implementation for the BookManager
 */
public class BookManagerImpl implements BookManager {
    private final BookDao bookDao;
    private final AuthorDao authorDao;

    @Autowired
    public BookManagerImpl(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    public List<Book> obtainAllBooks() {
        return bookDao.loadAll();
    }

    public List<Book> obtainFilteredBooks(BookSearchRequest searchRequest) {
        return bookDao.loadByFilter(searchRequest);
    }

    public void storeBook(final Book book) {
        for (Author author : book.getAuthors()) {
            if (author.getId() == null) {
                authorDao.save(author);
            }
        }
        bookDao.save(book);
    }

    public void internalUseStoreBook(final Book book) {
        for (Author author : book.getAuthors()) {
            if (author.getId() == null) {
                authorDao.save(author);
            }
        }
        bookDao.save(book);
    }
}
