package nl.gridshore.samples.hippo.impl;

import nl.gridshore.samples.hippo.RepoSession;

import javax.jcr.Session;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Workspace;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 26, 2008
 * Time: 10:57:55 PM
 * Implementation of the <code>RepoSession</code> interface. This wrapper is used to prevent client code from doing to
 * much in the repository in a non controller manner.
 */
public class WrappedSession implements RepoSession {
    private Session session;

    WrappedSession(Session session) {
        this.session = session;
    }

    public boolean isLive() {
        return session.isLive();
    }

    public void close() {
        session.logout();
    }

    public Node getRootNode() throws RepositoryException {
        return session.getRootNode();
    }

    public Workspace getWorkspace() {
        return session.getWorkspace();
    }
}
