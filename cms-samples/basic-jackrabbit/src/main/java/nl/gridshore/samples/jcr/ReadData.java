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
import javax.jcr.Session;

/**
 * Class used to demonstrate reading the repository using the root node
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

        Node childnode = rootNode.getNode("Jettro");

        StringBuilder printer = new StringBuilder();
        NodePrinter.printNode(childnode, printer);

        System.out.println(printer);
        logout(session);
    }
}
