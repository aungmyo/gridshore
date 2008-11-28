package nl.gridshore.samples.hippo;

import nl.gridshore.samples.hippo.impl.WrappedSession;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 26, 2008
 * Time: 10:56:29 PM
 * Callback method used by clients to interact with a cut down version of session
 */
public interface SessionCallback {
    /**
     * This method is called by the <code>RepoSessionTemplate</code>
     * @param session WrappedSession that can be used to interact with the repository
     * @return Node as a result from the implemented callback method
     * @throws RepositoryException Thrown when something goes wrong with the repository
     */
    Node readFromSession(WrappedSession session) throws RepositoryException;
}
