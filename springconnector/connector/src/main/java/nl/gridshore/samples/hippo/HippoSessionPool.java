package nl.gridshore.samples.hippo;

import javax.jcr.RepositoryException;

/**
 * Created by IntelliJ IDEA.
 * User: jettro coenradie
 * Date: Nov 27, 2008
 * Time: 1:58:04 PM
 * <p>This session pool can be used to create a session pool with special sessions with a read only interface. The returned
 * sessions are special objects wrapping the <code>javax.jcr.Session</code>. Connections taken from the pool should be
 * returned to the pool.</p>
 */
public interface HippoSessionPool {
    /**
     * Return a session from the session pool
     *
     * @return RepoSession from the session pool
     * @throws RepositoryException rethrown when creating a sessions goes wrong
     */
    RepoSession obtainSession() throws RepositoryException;

    /**
     * Return a session to the session pool.
     *
     * @param session PooledRepoSession that is returned to the pool
     */
    void returnSession(RepoSession session);
}
