package nl.gridshore.samples.hippo.impl;

import nl.gridshore.samples.hippo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import javax.jcr.RepositoryException;
import javax.jcr.Workspace;
import javax.jcr.query.QueryResult;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 26, 2008
 * Time: 11:02:25 PM
 * <p/>
 * Implementation of the <code>SessionTemplate</code> interface. This class is used to interact with the hippo
 * repository without the hassle of creating and closing sessions.
 */
public class RepoSessionTemplateImpl implements RepoSessionTemplate {
    private static Logger logger = LoggerFactory.getLogger(RepoSessionTemplateImpl.class);
    private HippoSessionFactory hippoSessionFactory;
    private HippoSessionPool hippoSessionPool;

    public QueryResult readFromSession(String username, String password, SessionCallback sessionCallback) throws RepositoryException {
        logger.debug("Read from session is called with username {}", username);
        WrappedSession session = new WrappedSession(hippoSessionFactory.createNewSession(username, password));
        return doReadFromSession(sessionCallback, session);
    }

    public QueryResult readFromSession(SessionCallback sessionCallback) throws RepositoryException {
        logger.debug("Read from session is called without username");
        RepoSession session = hippoSessionPool.obtainSession();
        return doReadFromSession(sessionCallback, session);
    }

    protected QueryResult doReadFromSession(SessionCallback sessionCallback, RepoSession session) throws RepositoryException {
        logger.debug("Execute the callback and close the session afterwards");
        QueryResult queryResult;
        try {
            Workspace workspace = session.getWorkspace();
            queryResult = sessionCallback.readFromSession(workspace.getQueryManager());
        } finally {
            session.close();
        }
        return queryResult;
    }

    @Required
    public void setHippoSessionFactory(HippoSessionFactory hippoSessionFactory) {
        this.hippoSessionFactory = hippoSessionFactory;
    }

    @Required
    public void setHippoSessionPool(HippoSessionPool hippoSessionPool) {
        this.hippoSessionPool = hippoSessionPool;
    }
}
