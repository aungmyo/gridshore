/*
 * Copyright (c) 2009. Gridshore
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.gridshore.samples.jcr;

import nl.gridshore.samples.jcr.util.NodePrinter;

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
        Node foundNode = rootNode.getNode("Jettro");

        StringBuilder printer = new StringBuilder();
        NodePrinter.printNode(foundNode,printer);

        foundNode.checkout();
        foundNode.getProperty("email").setValue("jettro@gridshore.nl");

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
        Node node = rootNode.addNode("Jettro");
        node.setProperty("title","Chief Architect");
        node.setProperty("email","jettro@jteam.nl");
        node.setProperty("age",36);

        node.addMixin("mix:versionable");
        session.save();
        node.checkin();
        logout(session);
    }
}
