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

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * Class to demonstrate versioning
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
        NodePrinter.printNode(foundNode, printer);

        foundNode.checkout();
        foundNode.getProperty("email").setValue("jettro@gridshore.nl");

        session.save();

        foundNode.checkin();
        printer.append("After change").append("\n");
        NodePrinter.printNode(foundNode, printer);

        NodePrinter.printVersions(foundNode, printer);
        logout(session);
        System.out.println(printer);

    }

    private void createVersionedNode() throws RepositoryException {
        Session session = getSession();
        Node rootNode = session.getRootNode();
        Node node = rootNode.addNode("Jettro");
        node.setProperty("title", "Chief Architect");
        node.setProperty("email", "jettro@jteam.nl");
        node.setProperty("age", 36);

        node.addMixin("mix:versionable");
        session.save();
        node.checkin();
        logout(session);
    }
}
