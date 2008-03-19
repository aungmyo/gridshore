package nl.gridshore.samples.books.integration;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.jpa.AbstractJpaTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.ObjectRetrievalFailureException;

import javax.persistence.EntityManagerFactory;

import nl.gridshore.samples.books.domain.Book;
import nl.gridshore.samples.books.domain.Author;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Mar 19, 2008
 * Time: 11:13:09 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-spring-integration.xml","classpath:spring-integration.xml"})
public class AuthorDaoTest  extends AbstractJpaTests {
    private AuthorDao authorDao;

    @Autowired
    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        super.setEntityManagerFactory(entityManagerFactory);
    }

    @Test
    @Transactional(readOnly = true)
    public void loadAllAuthors() {
        List<Author> allAuthors = authorDao.loadAll();
        assertNotNull("authors should have been returned", allAuthors);
        assertEquals("NUmber of items returned by doa is not correct",
                1,allAuthors.size());
    }

    @Test
    @Transactional(readOnly = true)
    public void loadAuthorById() {
        Author author = authorDao.loadById(1L);
        assertNotNull("This author should have been found",author);
        assertEquals("Id of author is not as expected", 1L, author.getId().longValue());
        assertEquals("Fullname of author is not as exepcetd", "Rod Johnson",author.getFullName());
        assertEquals("Email of author is not as exepcetd", "rod@springsource.com",author.getEmail());
    }

    @Test(expected = ObjectRetrievalFailureException.class)
    @Transactional(readOnly = true)
    public void throwExceptionByLoadingWithNonExistingId() {
        authorDao.loadById(99L);
    }

    @Test
    @Transactional
    public void createNewBook() {
        Author author = new Author();
        author.setFullName("Bram Smeets");
        author.setEmail("bram@jteam.nl");
        int numAuthors = authorDao.loadAll().size();
        authorDao.save(author);
        int newNumAuthors = authorDao.loadAll().size();
        assertEquals("Number of books is not changed as we expected it to change", 1, newNumAuthors - numAuthors);
    }

    @Test
    @Transactional
    public void addExistingAuthorToBook() {
        Author author = authorDao.loadById(1L);
        assertNotNull("This author should have been found", author);

        Book book = new Book();
        book.setTitle("Spring 2.5");
        sharedEntityManager.persist(book);
        author.addBook(book);
        assertNotNull("ID of new book should have been created", book.getId());
        assertNotNull("Book should have an author", book.getAuthors());
        assertEquals("Book should have an author", 1, book.getAuthors().size());
    }

}
