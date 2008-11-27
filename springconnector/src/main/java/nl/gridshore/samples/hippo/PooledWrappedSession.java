package nl.gridshore.samples.hippo;

import javax.jcr.Session;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 27, 2008
 * Time: 1:56:06 PM
 * Special extension to the <code>WrappedSession</code> that makes it easier to pool the session. The close method is
 * overridden to make sure the session are only closed by the Session pool.
 */
public class PooledWrappedSession extends WrappedSession implements RepoSession{
    private HippoSessionPool hippoSessionPool;

    /**
     * Constructor for the PooledWrappedSession taking the session to be wrapped and the pool to return the session
     * to when done.
     * @param session Session that is wrapped and contains the actal connection to the hippo repository
     * @param hippoSessionPool HippoSessionPool that this session is coming from.
     */
    public PooledWrappedSession(Session session, HippoSessionPool hippoSessionPool) {
        super(session);
        this.hippoSessionPool = hippoSessionPool;
    }

    @Override
    public void close() {
        hippoSessionPool.returnSession(this);
    }

    /**
     * Protected method that should only be called by the HttpSessionPool
     */
    public void doClose() {
        super.close();
    }
}
