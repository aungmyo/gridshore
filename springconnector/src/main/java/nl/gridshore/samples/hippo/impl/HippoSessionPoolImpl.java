package nl.gridshore.samples.hippo.impl;

import nl.gridshore.samples.hippo.HippoSessionPool;
import nl.gridshore.samples.hippo.PooledWrappedSession;
import nl.gridshore.samples.hippo.HippoSessionFactory;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import javax.jcr.RepositoryException;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 27, 2008
 * Time: 2:01:57 PM
 * <p>Implementation of a session pool with some very basic pooling implementation. The sessions are stored in a
 * FIFO queue. By requesting a session you get a session from the queue. You are required to return sessions
 * to the pool when you are done with it. The sessions should not be closed, since that will result in an exception
 * the next time the session is used.</p>
 * <p>The <code>HippoSessionFactory</code> is used to create new sessions with the default username and password.
 * When the pool is out of sessions, a new session is created and should be returned as well, since the client does
 * notice the difference.</p>
 * <p>Before a session is given to the caller, it is checked for the right state.</p>
 */
public class HippoSessionPoolImpl implements HippoSessionPool, InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(HippoSessionPoolImpl.class);

    private HippoSessionFactory hippoSessionFactory;
    private ConcurrentLinkedQueue<PooledWrappedSession> concurrentMap;
    private int amountSessionsAtStart = 10;

    public HippoSessionPoolImpl() {
        concurrentMap = new ConcurrentLinkedQueue<PooledWrappedSession>();
    }

    public PooledWrappedSession obtainSession() throws RepositoryException {
        PooledWrappedSession session = null;
        while (session == null && !concurrentMap.isEmpty()) {
            session = concurrentMap.poll();
            if (!session.isLive()) {
                logger.debug("The session with id {} is not live and will therefore be logged out.",session.toString());
                session.doClose();
                session = null;
            }
        }

        if (session == null) {
            Session hippoSession = hippoSessionFactory.createNewSession();
            session = new PooledWrappedSession(hippoSession,this);
            logger.debug("A new session object with id {} is created since the pool was empty",session.toString());
        } else {
            logger.debug("A pooled session object is returned from the pool with object id {}",session.toString());
        }

        return session;
    }

    public void returnSession(PooledWrappedSession session) {
        logger.debug("A session with id {} is returned to the pool",session.toString());
        concurrentMap.add(session);
    }

    public void afterPropertiesSet() throws Exception {
        logger.debug("Initialize {} sessions in the pool",amountSessionsAtStart);

        for (int i = 0; i < amountSessionsAtStart; i++) {
            Session session = hippoSessionFactory.createNewSession();
            PooledWrappedSession pooledWrappedSession = new PooledWrappedSession(session,this);
            logger.debug("A pooled session is created with id {}",pooledWrappedSession.toString());
            concurrentMap.add(pooledWrappedSession);
        }
    }

    public void setAmountSessionsAtStart(int amountSessionsAtStart) {
        this.amountSessionsAtStart = amountSessionsAtStart;
    }

    @Required
    public void setHippoSessionFactory(HippoSessionFactory hippoSessionFactory) {
        this.hippoSessionFactory = hippoSessionFactory;
    }
}
