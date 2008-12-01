package nl.gridshore.samples.hippo;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 26, 2008
 * Time: 10:54:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RepoSessionTemplate {
    Node readFromSession(String username, String password, SessionCallback sessionCallback) throws RepositoryException;
    Node readFromSession(SessionCallback sessionCallback) throws RepositoryException;
}
