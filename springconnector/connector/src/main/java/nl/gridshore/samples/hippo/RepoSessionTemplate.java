package nl.gridshore.samples.hippo;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.query.QueryResult;

/**
 * Created by IntelliJ IDEA.
 * User: jettro coenradie
 * Date: Nov 26, 2008
 * Time: 10:54:00 PM
 * <p>Interface for a session template that takes care of executing a query in the repository and handling the session
 * with the connection to the repository.</p>
 */
public interface RepoSessionTemplate {
    /**
     * Execute a query on the repository using a specific username and password.
     * @param username String representing the username to use to connect to the repository
     * @param password String representing the password of the username to connect to the repository
     * @param sessionCallback SessionCallback used to actually create the query to execute
     * @return The results of the executed query
     * @throws RepositoryException when something goes wrong with the repository
     */
    QueryResult readFromSession(String username, String password, SessionCallback sessionCallback) throws RepositoryException;

    /**
     * Execute a query on the repository with the default username password
     * @param sessionCallback SessionCallback used to actually create the query to execute
     * @return The results of the executed query
     * @throws RepositoryException when something goed wrong with the repository
     */
    QueryResult readFromSession(SessionCallback sessionCallback) throws RepositoryException;
}
