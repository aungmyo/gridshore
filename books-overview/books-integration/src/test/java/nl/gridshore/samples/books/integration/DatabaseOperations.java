package nl.gridshore.samples.books.integration;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.dao.DataAccessException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Mar 19, 2008
 * Time: 8:46:51 AM
 * Helper class for testing the dao's, inserts data
 */
public class DatabaseOperations extends JdbcDaoSupport {

    public DatabaseOperations() {
    }

    public void initializeDatabase() {
        createBooks();
        createAuthors();
    }

    private void createAuthors() {
        createAuthor(1, "Rod Johnson", "rod@springsource.com");
    }

    private void createBooks() {
        createBook(1, "Spring core");
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
