package nl.gridshore.samples.hippo;

import nl.gridshore.samples.hippo.impl.WrappedSession;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.query.QueryResult;
import javax.jcr.query.QueryManager;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 26, 2008
 * Time: 10:56:29 PM
 * Callback method used by clients execute a query
 */
public interface SessionCallback {
    /**
     * This method is called by the <code>RepoSessionTemplate</code>
     * @param queryManager QueryManager used to execute a query
     * @return Node as a result from the implemented callback method
     * @throws RepositoryException Thrown when something goes wrong with the repository
     */
    QueryResult readFromSession(QueryManager queryManager) throws RepositoryException;
}
