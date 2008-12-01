package nl.gridshore.samples.hippo;

import javax.jcr.Session;
import javax.jcr.LoginException;
import javax.jcr.RepositoryException;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 26, 2008
 * Time: 10:32:22 PM
 * This class acts as a factory object to create sessions based on userid and password
 */
public interface HippoSessionFactory {
    /**
     * Create a new session using the provided username and password
     *
     * @param username String representing the username used to create the session
     * @param password String representing the password used to create the session
     * @return A new Session that can be used for adding Nodes to the repository
     * @throws RepositoryException Thrown when something goes wrong during the creation of the session.
     */
    Session createNewSession(String username, String password) throws RepositoryException;

    /**
     * Create a new session based on a default username/password combination
     * @return Session with the default username password login
     * @throws RepositoryException thrown if something went wrong with the repository
     */
    Session createNewSession() throws RepositoryException;

}
