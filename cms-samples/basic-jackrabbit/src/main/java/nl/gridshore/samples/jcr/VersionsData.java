package nl.gridshore.samples.jcr;

import javax.jcr.*;
import javax.jcr.version.Version;
import javax.jcr.version.VersionHistory;
import javax.jcr.version.VersionIterator;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 30, 2009
 * Time: 1:16:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class VersionsData extends Base {
    public static void main(String[] args) {
        VersionsData versionsData = new VersionsData();
        versionsData.run();
    }

    @Override
    protected void doRun() throws Exception {
        createVersionedNode();

        Session session = getSession();

        Node rootNode = session.getRootNode();
        Node foundNode = rootNode.getNode("versionedItem");
        System.out.println("Primary type : " + foundNode.getPrimaryNodeType().getName());
        Property property = foundNode.getProperty("title");
        System.out.println("property : " + property.getPath());
        System.out.println("Name of found node : " + foundNode.getName());
        foundNode.checkout();
        foundNode.getProperty("title").setValue("changed title");
        session.save();
        foundNode.checkin();
        logout(session);

        printVersions();
    }

    private void printVersions() throws RepositoryException {
        Session session = getSession();
        Node rootNode = session.getRootNode();
        Node foundNode = rootNode.getNode("versionedItem");

        System.out.println("Name of found node : " + foundNode.getName());

        VersionHistory versionHistory = foundNode.getVersionHistory();
        VersionIterator versionIterator = versionHistory.getAllVersions();

        SimpleDateFormat simplDateFormat = new SimpleDateFormat("dd/MMM/yyyy (HH:mm:ss)");

        System.out.println("Versions");
        while (versionIterator.hasNext()) {
            Version version = versionIterator.nextVersion();
            System.out.println("create date " + simplDateFormat.format(version.getCreated().getTime()));
            NodeIterator nodes = version.getNodes();
            while (nodes.hasNext()) {
                Node frozenNode = nodes.nextNode();
                if (frozenNode.hasProperty("title")) {
                    System.out.println("title : " + frozenNode.getProperty("title").getString());
                } else {
                    System.out.println("Title property not found");
                }
            }
        }
        logout(session);
    }

    private void createVersionedNode() throws RepositoryException {
        Session session = getSession();
        Node rootNode = session.getRootNode();
        Node versionedNode = rootNode.addNode("versionedItem");
        versionedNode.addMixin("mix:versionable");
        versionedNode.setProperty("title", "versioned title");
        session.save();
        versionedNode.checkin();
        logout(session);
    }
}
