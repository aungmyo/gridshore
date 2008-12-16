package nl.gridshore.samples.hippo;

import nl.gridshore.samples.hippo.impl.WrappedSession;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.query.QueryResult;
import javax.jcr.query.QueryManager;

/**
 * Created by IntelliJ IDEA.
 * User: jettro coenradie
 * Date: Nov 26, 2008
 * Time: 10:56:29 PM
 * <p>Callback method used by clients execute a query. The following code block gives an idea about how you can use it
 * together with the RepoSessionTemplate</p>
 * <pre>
 * new SessionCallback() {
 *          public QueryResult readFromSession(QueryManager queryManager) throws RepositoryException {
 *              Query query = queryManager.createQuery("//element(*,defaultcontent:article)[jcr:like(@defaultcontent:title,'%"
 *                      + searchText + "%')]", Query.XPATH);
 *               return query.execute();
 *          }
 * }
 * </pre>
 * @see nl.gridshore.samples.hippo.RepoSessionTemplate
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
