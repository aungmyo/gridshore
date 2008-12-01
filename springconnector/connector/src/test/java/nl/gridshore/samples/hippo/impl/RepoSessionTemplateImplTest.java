package nl.gridshore.samples.hippo.impl;

import nl.gridshore.samples.hippo.*;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.QueryResult;

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
        final QueryResult mockNode = createMock(QueryResult.class);
        PooledSession mockPooledSession = createMock(PooledSession.class);
        expect(mockHippoSessionPool.obtainSession()).andReturn(mockPooledSession);

        mockPooledSession.close();
        expectLastCall().once();

        replay(mockHippoSessionPool,mockNode, mockPooledSession);

        QueryResult returnedNode = repoSessionTemplate.readFromSession(new SessionCallback() {
            public QueryResult readFromSession(WrappedSession session) throws RepositoryException {
                assertTrue(session instanceof PooledSession);
                assertNotNull(session);
                return mockNode;
            }
        });

        assertNotNull(returnedNode);
    }

    @Test
    public void executeCallback() throws RepositoryException {
        final QueryResult mockNode = createMock(QueryResult.class);
        Session mockSession = createMock(Session.class);
        expect(mockHippoSessionFactory.createNewSession("admin","admin")).andReturn(mockSession);

        mockSession.logout();
        expectLastCall().once();

        replay(mockHippoSessionFactory,mockNode,mockSession);

        QueryResult returnedNode = repoSessionTemplate.readFromSession("admin","admin",new SessionCallback() {
            public QueryResult readFromSession(WrappedSession session) throws RepositoryException {
                assertNotNull(session);
                return mockNode;
            }
        });

        assertNotNull(returnedNode);
    }

}
