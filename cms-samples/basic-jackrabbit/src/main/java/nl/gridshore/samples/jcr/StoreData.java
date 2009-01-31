package nl.gridshore.samples.jcr;

import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Node;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 30, 2009
 * Time: 11:38:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class StoreData extends Base {
     public static void main(String[] args) {
        StoreData storeData = new StoreData();
        storeData.run();
     }

    @Override
    protected void doRun() throws Exception {
        Session session = getSession();

        Node rootNode = session.getRootNode();
        Node node = rootNode.addNode("NewNode");

        node.setProperty("title","Chief Architect");
        node.setProperty("email","jettro@jteam.nl");
        node.setProperty("age",36);

        session.save();
        logout(session);
    }
}
