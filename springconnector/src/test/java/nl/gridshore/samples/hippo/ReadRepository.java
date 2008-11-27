package nl.gridshore.samples.hippo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;

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
        for (int i = 0; i < 100; i++) {
            readNodes(repoSessionTemplate);
        }
    }

    private static void readNodes(RepoSessionTemplate repoSessionTemplate) throws RepositoryException {
        Node rootNode;

        rootNode = repoSessionTemplate.readFromSession(new SessionCallback() {
            public Node readFromSession(WrappedSession session) throws RepositoryException {
                return session.getRootNode();
            }
        });
        NodeIterator nodes = rootNode.getNodes();
        while (nodes.hasNext()) {
            Node node = nodes.nextNode();
        }
    }
}
