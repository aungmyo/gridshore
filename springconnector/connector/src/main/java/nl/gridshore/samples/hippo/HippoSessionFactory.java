package nl.gridshore.samples.hippo;

import javax.jcr.LoginException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * Created by IntelliJ IDEA.
 * User: jettro coenradie
 * Date: Nov 26, 2008
 * Time: 10:32:22 PM
 * <p>This class acts as a factory object to create sessions based on userid and password. It is also possible to
 * create a session without a username and password. In that case the actual implementation should have a mechanism to
 * configure a default password.</p>
 */
public interface HippoSessionFactory {
    /**
     * Create a new session using the provided username and password
     *
     * @param username String representing the username used to create the session
     * @param password String representing the password used to create the session
     * @return A new Session that can be used for adding Nodes to the repository
     * @throws RepositoryException      Rethrown from the underlying hippo login method.
     * @throws javax.jcr.LoginException Rethrown from the underlying hippo login method.
     */
    Session createNewSession(String username, String password) throws LoginException, RepositoryException;

    /**
     * Create a new session based on a default username/password combination
     *
     * @return Session with the default username password login
     * @throws RepositoryException      Rethrown from the underlying hippo login method.
     * @throws javax.jcr.LoginException Rethrown from the underlying hippo login method.
     */
    Session createNewSession() throws LoginException, RepositoryException;

}
