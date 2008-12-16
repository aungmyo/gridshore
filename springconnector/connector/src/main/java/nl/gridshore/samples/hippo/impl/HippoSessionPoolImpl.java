package nl.gridshore.samples.hippo.impl;

import nl.gridshore.samples.hippo.HippoSessionFactory;
import nl.gridshore.samples.hippo.HippoSessionPool;
import nl.gridshore.samples.hippo.RepoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.concurrent.ConcurrentLinkedQueue;

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
 * <p>Sessions are not tracked, if clients do not return sessions, the pool does not function very well and memory leaks
 * could occur if the clients keep a reference to the sessions. Which is very easy when for instance they are storing
 * nodes.</p>
 * <p><strong>Beware</strong>, session pooling is interesting for performance reasons. However the default api of jcr
 * gives a user access to the underlying session of a Node, which gives the Workspace and the repository. That way a
 * user can even create his own session using this repository. Therefore the pooling only works if your clients
 * use it the way it should be used.</p>
 */
public class HippoSessionPoolImpl implements HippoSessionPool, InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(HippoSessionPoolImpl.class);

    private HippoSessionFactory hippoSessionFactory;
    private ConcurrentLinkedQueue<PooledSession> concurrentMap;
    private int amountSessionsAtStart = 10;

    /**
     * Default constructor initializes the queue
     */
    public HippoSessionPoolImpl() {
        concurrentMap = new ConcurrentLinkedQueue<PooledSession>();
    }

    /**
     * {@inheritDoc}
     * <p>Obtain a session from the pool and check if it is still active before returning it. If no sessions
     * are available, create a new one.</p>
     */
    public RepoSession obtainSession() throws RepositoryException {
        PooledSession session = null;
        while (session == null && !concurrentMap.isEmpty()) {
            session = concurrentMap.poll();
            if (!session.isLive()) {
                logger.debug("The session with id {} is not live and will therefore be logged out.", session.toString());
                session.doClose();
                session = null;
            }
        }

        if (session == null) {
            Session hippoSession = hippoSessionFactory.createNewSession();
            session = new PooledSession(hippoSession, this);
            logger.debug("A new session object with id {} is created since the pool was empty", session.toString());
        } else {
            logger.debug("A pooled session object is returned from the pool with object id {}", session.toString());
        }

        return session;
    }

    /**
     * {@inheritDoc}
     * <p>Puts the returned session back into the pool.</p>
     */
    public void returnSession(RepoSession session) {
        if (session instanceof PooledSession) {
            logger.debug("A session with id {} is returned to the pool", session.toString());
            concurrentMap.add((PooledSession) session);
        } else {
            logger.debug("A session with id {} is not a pooled session", session.toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void afterPropertiesSet() throws Exception {
        logger.debug("Initialize {} sessions in the pool", amountSessionsAtStart);

        for (int i = 0; i < amountSessionsAtStart; i++) {
            Session session = hippoSessionFactory.createNewSession();
            PooledSession pooledSession = new PooledSession(session, this);
            logger.debug("A pooled session is created with id {}", pooledSession.toString());
            concurrentMap.add(pooledSession);
        }
    }

    /**
     * Method that must be called before the after properties set method is called. The provided number of sessions
     * is used to create the initial pool.
     * @param amountSessionsAtStart int representing the amount of sessions to start the pool with
     */
    public void setAmountSessionsAtStart(int amountSessionsAtStart) {
        this.amountSessionsAtStart = amountSessionsAtStart;
    }

    /**
     * Setter used to set the required HippoSessionFactory
     * @param hippoSessionFactory HippoSessionFactory used to create the sessions
     */
    @Required
    public void setHippoSessionFactory(HippoSessionFactory hippoSessionFactory) {
        this.hippoSessionFactory = hippoSessionFactory;
    }
}
