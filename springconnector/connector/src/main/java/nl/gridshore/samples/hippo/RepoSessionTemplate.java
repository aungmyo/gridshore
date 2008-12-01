package nl.gridshore.samples.hippo;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.query.QueryResult;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 26, 2008
 * Time: 10:54:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RepoSessionTemplate {
    QueryResult readFromSession(String username, String password, SessionCallback sessionCallback) throws RepositoryException;
    QueryResult readFromSession(SessionCallback sessionCallback) throws RepositoryException;
}
