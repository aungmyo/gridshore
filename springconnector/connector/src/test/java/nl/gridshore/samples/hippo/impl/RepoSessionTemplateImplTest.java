package nl.gridshore.samples.hippo.impl;

import nl.gridshore.samples.hippo.HippoSessionFactory;
import nl.gridshore.samples.hippo.HippoSessionPool;
import nl.gridshore.samples.hippo.SessionCallback;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Workspace;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

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
        final QueryResult mockQueryResult = createMock(QueryResult.class);
        PooledSession mockPooledSession = createMock(PooledSession.class);
        Workspace mockWorkspace = createMock(Workspace.class);
        QueryManager mockQueryManager = createMock(QueryManager.class);

        expect(mockHippoSessionPool.obtainSession()).andReturn(mockPooledSession);
        expect(mockPooledSession.getWorkspace()).andReturn(mockWorkspace);
        expect(mockWorkspace.getQueryManager()).andReturn(mockQueryManager);
        mockPooledSession.close();
        expectLastCall().once();

        replay(mockHippoSessionPool, mockQueryResult, mockPooledSession, mockWorkspace, mockQueryManager);

        QueryResult returnedNode = repoSessionTemplate.readFromSession(new SessionCallback() {
            public QueryResult readFromSession(QueryManager queryManager) throws RepositoryException {
                assertNotNull(queryManager);
                return mockQueryResult;
            }
        });

        assertNotNull(returnedNode);
        verify(mockHippoSessionPool, mockQueryResult, mockPooledSession, mockWorkspace, mockQueryManager);
    }

    @Test
    public void executeCallback() throws RepositoryException {
        final QueryResult mockQueryResult = createMock(QueryResult.class);
        Session mockSession = createMock(Session.class);
        Workspace mockWorkspace = createMock(Workspace.class);
        QueryManager mockQueryManager = createMock(QueryManager.class);
        expect(mockHippoSessionFactory.createNewSession("admin", "admin")).andReturn(mockSession);
        expect(mockSession.getWorkspace()).andReturn(mockWorkspace);
        expect(mockWorkspace.getQueryManager()).andReturn(mockQueryManager);

        mockSession.logout();
        expectLastCall().once();

        replay(mockHippoSessionFactory, mockQueryResult, mockSession, mockWorkspace, mockQueryManager);

        QueryResult returnedNode = repoSessionTemplate.readFromSession("admin", "admin", new SessionCallback() {
            public QueryResult readFromSession(QueryManager queryManager) throws RepositoryException {
                return mockQueryResult;
            }
        });

        assertNotNull(returnedNode);
        verify(mockHippoSessionFactory, mockQueryResult, mockSession, mockWorkspace, mockQueryManager);
    }

}
