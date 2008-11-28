package nl.gridshore.samples.hippo.impl;

import org.hippoecm.repository.HippoRepository;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import nl.gridshore.samples.hippo.ConfigurationException;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 27, 2008
 * Time: 8:09:06 PM
 * Testcase for the implementation of the HippoSessionFactory interface
 */
public class HippoSessionFactoryImplTest {
    private HippoSessionFactoryImpl hippoSessionFactory;
    private HippoRepository mockHippoRepository;

    @Before
    public void init() {
        mockHippoRepository = createMock(HippoRepository.class);
        hippoSessionFactory = new HippoSessionFactoryImpl();
        hippoSessionFactory.setHippoRepository(mockHippoRepository);
    }

    @Test
    public void obtainingNewConnectionWithoutDefaultUsernameSet() {
        replay(mockHippoRepository);
        try {
            hippoSessionFactory.createNewSession();
            fail("a repository exception should have been thrown");
        } catch (ConfigurationException ex) {
            // as expected since we are missing the username and the password
        } catch (RepositoryException e) {
            fail("The exception should have been cought ealier");
        }
    }

    @Test
    public void obtainNewConnectionWithExistingusername() throws RepositoryException {
        Session mockSession = createMock(Session.class);
        String username = "test";
        String password = "testpwd";
        expect(mockHippoRepository.login(eq(username), aryEq(password.toCharArray()))).andReturn(mockSession);
        replay(mockHippoRepository, mockSession);

        hippoSessionFactory.setDefaultPassword(password);
        hippoSessionFactory.setDefaultUsername(username);

        Session session = hippoSessionFactory.createNewSession();
        assertNotNull(session);
        verify(mockHippoRepository, mockSession);
    }
}
