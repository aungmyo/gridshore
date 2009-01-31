package nl.gridshore.samples.jcr;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.Session;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 29, 2009
 * Time: 9:45:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReadData extends Base {
    public static void main(String[] args) {
        ReadData readData = new ReadData();
        readData.run();
    }

    @Override
    protected void doRun() throws Exception {
        Session session = getReadonlySession();
        Node rootNode = session.getRootNode();
        Node childnode = null;
        try {
            childnode = rootNode.getNode("foo");
            try {
                Property prop = childnode.getProperty("bar");
                System.out.println("value of /foo@bar: " + prop.getString());
            } catch (PathNotFoundException pnfe) {
                System.out.println("/foo@bar not found.");
            }
        } catch (PathNotFoundException pnfe) {
            System.out.println("/foo not found.");
        }

        logout(session);
    }
}
