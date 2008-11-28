package nl.gridshore.samples.hippo.impl;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;
import nl.gridshore.samples.hippo.HippoSessionFactory;
import nl.gridshore.samples.hippo.impl.PooledSession;

import javax.jcr.Session;

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
        replay(mockHippoSessionFactory,mockSession);

        hippoSessionPool.afterPropertiesSet();

        PooledSession session = hippoSessionPool.obtainSession();

        assertNotNull(session);
        verify(mockHippoSessionFactory,mockSession);
    }

    @Test
    public void testObtainingAConnection_whenpoolisempty() throws Exception {
        Session mockSession1 = createMock(Session.class);
        Session mockSession2 = createMock(Session.class);
        expect(mockHippoSessionFactory.createNewSession()).andReturn(mockSession1);
        expect(mockHippoSessionFactory.createNewSession()).andReturn(mockSession2);

        expect(mockSession1.isLive()).andReturn(true);
        replay(mockHippoSessionFactory,mockSession1,mockSession2);

        hippoSessionPool.afterPropertiesSet();

        PooledSession session1 = hippoSessionPool.obtainSession();
        PooledSession session2 = hippoSessionPool.obtainSession();

        assertNotNull(session1);
        assertNotNull(session2);

        verify(mockHippoSessionFactory,mockSession1,mockSession2);
    }

}
