package nl.gridshore.samples.hippo;

import nl.gridshore.samples.hippo.impl.PooledSession;

import javax.jcr.RepositoryException;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 27, 2008
 * Time: 1:58:04 PM
 * This session pool can be used to create a session pool with special sessions with a read only interface. The returned
 * sessions are special objects wrapping the <code>javax.jcr.Session</code>. Connections taken from the pool should be
 * returned to the pool.
 */
public interface HippoSessionPool {
    PooledSession obtainSession() throws RepositoryException;
    void returnSession(PooledSession session);
}
