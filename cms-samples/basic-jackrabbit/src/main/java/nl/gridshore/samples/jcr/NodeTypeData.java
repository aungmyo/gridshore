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

import javax.jcr.Session;
import javax.jcr.nodetype.*;

/**
 * <p>Class to demonstrate the retrieval of all possible NodeTypes</p>
 *
 * @author jettro coenradie
 *         Date: Feb 1, 2009
 */
public class NodeTypeData extends Base {

    public static void main(String[] args) {
        NodeTypeData typeData = new NodeTypeData();
        typeData.run();
    }

    @Override
    protected void doRun() throws Exception {
        Session session = getSession();
        NodeTypeManager typeManager = session.getWorkspace().getNodeTypeManager();
        NodeTypeIterator typeIterator = typeManager.getAllNodeTypes();
        StringBuilder printer = new StringBuilder();
        printer.append("All nodetypes \n");
        while (typeIterator.hasNext()) {
            NodeType nodeType = typeIterator.nextNodeType();
            printer.append("  Name : ").append(nodeType.getName()).append("\n")
                    .append("  is mixin : ").append(nodeType.isMixin()).append("\n");
            printer.append("  Node Definition :");
            for (NodeDefinition nodeDefinition : nodeType.getChildNodeDefinitions()) {
                printer.append(nodeDefinition.getName()).append(" ");
            }
            printer.append("\n");
            printer.append("  Property Definition :");
            for (PropertyDefinition propertyDefinition : nodeType.getPropertyDefinitions()) {
                printer.append(propertyDefinition.getName()).append(" ");
            }
            printer.append("\n\n");
        }
        logout(session);
        System.out.println(printer);
    }
}
