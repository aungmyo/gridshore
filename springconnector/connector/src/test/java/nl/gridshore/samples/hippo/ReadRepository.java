package nl.gridshore.samples.hippo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Workspace;
import javax.jcr.query.QueryManager;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;

import nl.gridshore.samples.hippo.impl.WrappedSession;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 26, 2008
 * Time: 10:00:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReadRepository {
    public static void main(String[] args) throws RepositoryException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"connector-config.xml"});

        RepoSessionTemplate repoSessionTemplate = (RepoSessionTemplate) ctx.getBean("sessionTemplate");
        readNodes(repoSessionTemplate);
    }

    private static void readNodes(RepoSessionTemplate repoSessionTemplate) throws RepositoryException {
        QueryResult queryResult;

        queryResult = repoSessionTemplate.readFromSession(new SessionCallback() {
            public QueryResult readFromSession(QueryManager queryManager) throws RepositoryException {
                Query query = queryManager.createQuery(
                        "//element(*,defaultcontent:article)[jcr:like(@defaultcontent:title,'%title%')]", Query.XPATH);
                return query.execute();
            }
        });
        NodeIterator nodes = queryResult.getNodes();
        while (nodes.hasNext()) {
            Node node = nodes.nextNode();
            System.out.println("Node title : " + node.getName());
        }
    }
}
