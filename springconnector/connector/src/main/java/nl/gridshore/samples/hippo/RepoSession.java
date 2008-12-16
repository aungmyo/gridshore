package nl.gridshore.samples.hippo;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Workspace;

/**
 * Created by IntelliJ IDEA.
 * User: jettro coenradie
 * Date: Nov 27, 2008
 * Time: 1:53:41 PM
 * <p>Interface used by clients to interact with the repository. Most of the methods are delegate methods to the
 * wrapped @see javax.jcr.Session.</p>
 */
public interface RepoSession {
    /**
     * close the current wrapped session, can mean different things, logout for a normal session, return to the
     * pool for a pooled session.
     */
    void close();

    /**
     * @see javax.jcr.Session
     * @return Node as returned by the wrapped method
     * @throws javax.jcr.RepositoryException when the wrapped method throws an exception
     */
    Node getRootNode() throws RepositoryException;

    /**
     * Can be used to check if the wrapped session is still alive
     * @return boolean yes if the wrapped session is still alive
     */
    boolean isLive();

    /**
     * Returns the workspace the session is created for
     * @return Workspace for the created session
     */
    Workspace getWorkspace();
}
