package nl.gridshore.samples.hippo.impl;

import nl.gridshore.samples.hippo.ConfigurationException;
import nl.gridshore.samples.hippo.HippoSessionFactory;
import org.hippoecm.repository.HippoRepository;
import org.springframework.beans.factory.annotation.Required;

import javax.jcr.LoginException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * Created by IntelliJ IDEA.
 * User: jettro coenradie
 * Date: Nov 26, 2008
 * Time: 10:37:06 PM
 * <p>Implementation of the session factory that can be used with a default username/password, that should be provided
 * using setters. If they are not provided the normal exception is thrown for not providing a valid
 * username or password</p>
 */
public class HippoSessionFactoryImpl implements HippoSessionFactory {
    private HippoRepository hippoRepository;
    private String defaultUsername;
    private String defaultPassword;

    /**
     * {@inheritDoc}
     */
    public Session createNewSession() throws LoginException, RepositoryException {
        checkConfiguration();
        return createNewSession(defaultUsername, defaultPassword);
    }

    /**
     * {@inheritDoc}
     */
    public Session createNewSession(String username, String password) throws LoginException, RepositoryException {
        if (username == null || "".equals(username)) {
            throw new LoginException("Problem with the username");
        }
        if (password == null || "".equals(password)) {
            throw new LoginException("Problem with the password");
        }

        return hippoRepository.login(username, password.toCharArray());
    }

    private void checkConfiguration() {
        if (defaultPassword == null || defaultUsername == null) {
            throw new ConfigurationException("You should provide a default username and password when using this method");
        }
    }

    @Required
    public void setHippoRepository(HippoRepository hippoRepository) {
        this.hippoRepository = hippoRepository;
    }

    public void setDefaultUsername(String defaultUsername) {
        this.defaultUsername = defaultUsername;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

}
