package nl.gridshore.samples.hippo.impl;

import nl.gridshore.samples.hippo.*;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import static org.easymock.classextension.EasyMock.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 27, 2008
 * Time: 8:41:06 PM
 * test case for testin the RepoSessionTemplateImpl class
 */
public class RepoSessionTemplateImplTest {
    private RepoSessionTemplateImpl repoSessionTemplate;
    private HippoSessionPool mockHippoSessionPool;
    private HippoSessionFactory mockHippoSessionFactory;

    @Before
    public void init() {
        mockHippoSessionPool = createMock(HippoSessionPool.class);
        mockHippoSessionFactory = createMock(HippoSessionFactory.class);

        repoSessionTemplate = new RepoSessionTemplateImpl();
        repoSessionTemplate.setHippoSessionPool(mockHippoSessionPool);
        repoSessionTemplate.setHippoSessionFactory(mockHippoSessionFactory);
    }

    @Test
    public void executePooledCallback() throws RepositoryException {
        final Node mockNode = createMock(Node.class);
        PooledWrappedSession mockPooledWrappedSession = createMock(PooledWrappedSession.class);
        expect(mockHippoSessionPool.obtainSession()).andReturn(mockPooledWrappedSession);

        mockPooledWrappedSession.close();
        expectLastCall().once();

        replay(mockHippoSessionPool,mockNode,mockPooledWrappedSession);

        Node returnedNode = repoSessionTemplate.readFromSession(new SessionCallback() {
            public Node readFromSession(WrappedSession session) throws RepositoryException {
                assertTrue(session instanceof PooledWrappedSession);
                assertNotNull(session);
                return mockNode;
            }
        });

        assertNotNull(returnedNode);
    }

    @Test
    public void executeCallback() throws RepositoryException {
        final Node mockNode = createMock(Node.class);
        Session mockSession = createMock(Session.class);
        expect(mockHippoSessionFactory.createNewSession("admin","admin")).andReturn(mockSession);

        mockSession.logout();
        expectLastCall().once();

        replay(mockHippoSessionFactory,mockNode,mockSession);

        Node returnedNode = repoSessionTemplate.readFromSession("admin","admin",new SessionCallback() {
            public Node readFromSession(WrappedSession session) throws RepositoryException {
                assertNotNull(session);
                return mockNode;
            }
        });

        assertNotNull(returnedNode);
    }

}
