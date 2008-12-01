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
//        for (int i = 0; i < 100; i++) {
            readNodes(repoSessionTemplate);
//        }
    }

    private static void readNodes(RepoSessionTemplate repoSessionTemplate) throws RepositoryException {
        QueryResult queryResult;

        queryResult = repoSessionTemplate.readFromSession(new SessionCallback() {
            public QueryResult readFromSession(WrappedSession session) throws RepositoryException {
                Workspace workspace = session.getWorkspace();
                QueryManager queryManager = workspace.getQueryManager();
//                Query query = queryManager.createQuery("//element(*,poc:document)", Query.XPATH);
                Query query = queryManager.createQuery("//element(*,poc:document)[jcr:like(@poc:introductie,'%EPD%')]", Query.XPATH);
                QueryResult queryResult = query.execute();
                return queryResult;
            }
        });
        NodeIterator nodes = queryResult.getNodes();
        while (nodes.hasNext()) {
            Node node = nodes.nextNode();
            System.out.println("Node title : " + node.getName());
        }
    }
}
