package nl.gridshore.samples.hippo.impl;

import nl.gridshore.samples.hippo.HippoSessionFactory;
import nl.gridshore.samples.hippo.RepoSession;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

import javax.jcr.Session;
import javax.jcr.LoginException;
import javax.jcr.RepositoryException;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 27, 2008
 * Time: 7:07:16 PM
 * Test case for the session pooling
 */
public class HippoSessionPoolImplTest {
    private HippoSessionPoolImpl hippoSessionPool;

    // mocks
    private HippoSessionFactory mockHippoSessionFactory;

    @Before
    public void init() throws Exception {
        mockHippoSessionFactory = createMock(HippoSessionFactory.class);

        hippoSessionPool = new HippoSessionPoolImpl();
        hippoSessionPool.setAmountSessionsAtStart(1);
        hippoSessionPool.setHippoSessionFactory(mockHippoSessionFactory);
    }

    @Test
    public void testObtainingAConnection() throws Exception {
        Session mockSession = createMock(Session.class);
        expect(mockHippoSessionFactory.createNewSession()).andReturn(mockSession);

        expect(mockSession.isLive()).andReturn(true);
        replay(mockHippoSessionFactory, mockSession);

        // The following method is normally called by the spring container
        hippoSessionPool.afterPropertiesSet();

        RepoSession session = hippoSessionPool.obtainSession();

        assertNotNull(session);
        verify(mockHippoSessionFactory, mockSession);
    }

    @Test
    public void testObtainingAConnection_whenpoolisempty() throws Exception {
        Session mockSession1 = createMock(Session.class);
        Session mockSession2 = createMock(Session.class);
        expect(mockHippoSessionFactory.createNewSession()).andReturn(mockSession1);
        expect(mockHippoSessionFactory.createNewSession()).andReturn(mockSession2);

        expect(mockSession1.isLive()).andReturn(true);
        replay(mockHippoSessionFactory, mockSession1, mockSession2);

        hippoSessionPool.afterPropertiesSet();

        RepoSession session1 = hippoSessionPool.obtainSession();
        RepoSession session2 = hippoSessionPool.obtainSession();

        assertNotNull(session1);
        assertNotNull(session2);

        verify(mockHippoSessionFactory, mockSession1, mockSession2);
    }

    @Test
    public void testObtainingAConnection_notlive() throws Exception {
        Session mockSession = createMock(Session.class);
        expect(mockHippoSessionFactory.createNewSession()).andReturn(mockSession);

        expect(mockSession.isLive()).andReturn(false);
        mockSession.logout();
        expectLastCall().once();
        Session newMockSession = createMock(Session.class);
        expect(mockHippoSessionFactory.createNewSession()).andReturn(newMockSession);
        replay(mockHippoSessionFactory, mockSession, newMockSession);

        // The following method is normally called by the spring container
        hippoSessionPool.afterPropertiesSet();

        RepoSession session = hippoSessionPool.obtainSession();

        assertNotNull(session);
        verify(mockHippoSessionFactory, mockSession, newMockSession);

    }

    @Test
    public void testReturningANonPooledSession() throws Exception {
        Session mockSession = createMock(Session.class);
        Session mockSession2 = createMock(Session.class);
        expect(mockHippoSessionFactory.createNewSession()).andReturn(mockSession);
        expect(mockSession.isLive()).andReturn(true);

        // since we are not dealing with a pooled session, we expect an additional call to the factory
        expect(mockHippoSessionFactory.createNewSession()).andReturn(mockSession2);

        RepoSession mockRepoSession = createMock(RepoSession.class);

        replay(mockSession, mockSession2, mockHippoSessionFactory, mockRepoSession);

        hippoSessionPool.afterPropertiesSet();

        hippoSessionPool.returnSession(mockRepoSession);
        assertNotNull(hippoSessionPool.obtainSession());
        assertNotNull(hippoSessionPool.obtainSession());

        verify(mockSession, mockSession2, mockHippoSessionFactory, mockRepoSession);

    }

    @Test
    public void testReturningAPooledSession() throws Exception {
        Session mockSession = createMock(Session.class);
        expect(mockHippoSessionFactory.createNewSession()).andReturn(mockSession);
        expect(mockSession.isLive()).andReturn(true).times(2);


        replay(mockSession, mockHippoSessionFactory);

        hippoSessionPool.afterPropertiesSet();

        RepoSession repoSession = hippoSessionPool.obtainSession();
        assertNotNull(repoSession);
        hippoSessionPool.returnSession(repoSession);
        assertNotNull(hippoSessionPool.obtainSession());

        verify(mockSession,  mockHippoSessionFactory);

    }
}
