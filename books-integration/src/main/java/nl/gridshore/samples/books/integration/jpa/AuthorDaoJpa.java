package nl.gridshore.samples.books.integration.jpa;

import nl.gridshore.samples.books.domain.Author;
import nl.gridshore.samples.books.integration.AuthorDao;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Mar 19, 2008
 * Time: 8:40:44 AM
 * Author data access implementation using jpa
 */
public class AuthorDaoJpa extends BaseDaoJpa<Author> implements AuthorDao {
    public AuthorDaoJpa() {
        super(Author.class, "Author");
    }
}
