package nl.gridshore.samples.books.integration;

import org.springframework.test.jpa.AbstractJpaTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.junit.runner.RunWith;
import org.junit.Test;

import java.util.List;

import nl.gridshore.samples.books.domain.Book;
import nl.gridshore.samples.books.domain.Author;

import javax.persistence.EntityManagerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Mar 19, 2008
 * Time: 9:03:29 AM
 * Test class for the BookDao implementation
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-spring-integration.xml","classpath:spring-integration.xml"})
public class BookDaoTest extends AbstractJpaTests {
    private BookDao bookDao;

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        super.setEntityManagerFactory(entityManagerFactory);
    }

    @Test
    @Transactional(readOnly = true)
    public void loadAllBooks() {
        List<Book> allBooks = bookDao.loadAll();
        assertNotNull("books should have been returned", allBooks);
        assertEquals("NUmber of items returned by doa is not correct",
                1,allBooks.size());
    }

    @Test
    @Transactional(readOnly = true)
    public void loadBookById() {
        Book book = bookDao.loadById(1L);
        assertNotNull("This book should have been found",book);
        assertEquals("Id of book is not as expected", 1L, book.getId().longValue());
        assertEquals("Title of book is not as exepcetd", "Spring core",book.getTitle());
    }

    @Test(expected = ObjectRetrievalFailureException.class)
    @Transactional(readOnly = true)
    public void throwExceptionByLoadingWithNonExistingId() {
        bookDao.loadById(99L);
    }

    @Test
    @Transactional
    public void createNewBook() {
        Book book = new Book();
        book.setTitle("Hibernate");
        int numBooks = bookDao.loadAll().size();
        bookDao.save(book);
        int newNumBooks = bookDao.loadAll().size();
        assertEquals("Number of books is not changed as we expected it to change", 1, newNumBooks - numBooks);
    }

    @Test
    @Transactional
    public void addExistingAuthorToBook() {
        Book book = bookDao.loadById(1L);
        assertNotNull("This book should have been found",book);
        Author author = new Author();
        author.setEmail("alef@springsource.com");
        author.setFullName("Alef Arendsen");
        book.addAuthor(author);
        sharedEntityManager.persist(author);

        assertNotNull("ID of new author should have been created", author.getId());
        assertNotNull("Author should have a book", author.getBooks());
        assertEquals("Author should have a book", 1, author.getBooks().size());
    }
}
