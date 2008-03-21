package nl.gridshore.samples.books.business;

import org.junit.Before;
import org.junit.Test;
import nl.gridshore.samples.books.integration.BookDao;
import nl.gridshore.samples.books.integration.AuthorDao;
import nl.gridshore.samples.books.business.impl.BookManagerImpl;
import nl.gridshore.samples.books.domain.Book;
import nl.gridshore.samples.books.domain.Author;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Mar 19, 2008
 * Time: 7:46:03 PM
 * Test class for the BookManager class
 */
public class BookManagerTest {
    private BookManager bookManager;
    private BookDao mockBookDao;
    private AuthorDao mockAuthorDao;

    @Before
    public void initialize() {
        mockBookDao = createMock(BookDao.class);
        mockAuthorDao = createMock(AuthorDao.class);
        bookManager = new BookManagerImpl(mockBookDao,mockAuthorDao);
    }

    @Test
    public void obtainistOfAllBooks() {
        List<Book> returnedBooks = new ArrayList<Book>();
        returnedBooks.add(createNewBook());
        expect(mockBookDao.loadAll()).andReturn(returnedBooks);

        replay(mockBookDao);

        List<Book> foundBooks = bookManager.obtainAllBooks();

        verify(mockBookDao);
        assertNotNull("A non null List object should have been returned",foundBooks);
        assertEquals("Number of found books is not corect",1,foundBooks.size());
    }

    @Test
    public void storeBookWithNewAuthors() {
        Book book = createNewBook();
        Author author = new Author("test@test.nl","test name");
        book.addAuthor(author);

        expect(mockAuthorDao.save(author)).andReturn(author);
        expect(mockBookDao.save(book)).andReturn(book);

        replay(mockAuthorDao, mockBookDao);

        bookManager.storeBook(book);

        verify(mockAuthorDao, mockBookDao);
    }

    private Book createNewBook() {
        return new Book(1L,"My first book","isbn:12345678");
    }
}
