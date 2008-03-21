package nl.gridshore.samples.books.integration;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Mar 19, 2008
 * Time: 8:36:45 PM
 * This bean is only used to fill the database with some preset values. Do not use this is production
 */
public class PrefillDatabaseOperations  extends JdbcDaoSupport {

    @Transactional
    public void initializeDatabase() {
        createBooks();
        createAuthors();
    }

    private void createAuthors() {
        createAuthor(1, "Rod Johnson", "rod@springsource.com");
        createAuthor(2, "Gavin King", "gaven.king@hibernate.com");
    }

    private void createBooks() {
        createBook(1, "Spring core");
        createBook(2, "Hibernate in action");
    }

    private void createBook(final long id, final String title) {
        String insert = "insert into books (id,title) values(?,?);";
        getJdbcTemplate().execute(insert, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setLong(1,id);
                preparedStatement.setString(2, title);
                preparedStatement.execute();
                return null;
            }
        });
    }

    private void createAuthor(final long id, final String fullname, final String email) {
        String insert = "insert into authors (id,fullname,email) values(?,?,?);";
        getJdbcTemplate().execute(insert, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setLong(1, id);
                preparedStatement.setString(2, fullname);
                preparedStatement.setString(3, email);
                preparedStatement.execute();
                return null;
            }
        });
    }

}
