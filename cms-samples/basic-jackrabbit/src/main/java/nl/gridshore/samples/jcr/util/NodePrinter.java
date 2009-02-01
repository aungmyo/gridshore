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

package nl.gridshore.samples.jcr.util;

import javax.jcr.*;
import javax.jcr.query.QueryResult;
import javax.jcr.version.VersionHistory;
import javax.jcr.version.VersionIterator;
import javax.jcr.version.Version;
import javax.jcr.nodetype.NodeType;
import java.text.SimpleDateFormat;

/**
 * <p>Helper class for printing nodes</p>
 *
 * @author jettro coenradie
 *         Date: Jan 31, 2009
 */
public class NodePrinter {
    private NodePrinter() {}

    public static void printNode(Node node, StringBuilder printer) throws RepositoryException {
        checkPrinter(printer);
        checkNode(node);
        printer.append("Node : \n")
                .append("  Name : ").append(node.getName()).append("\n")
                .append("  Path : ").append(node.getPath()).append("\n")
                .append("  Primary type : ").append(node.getPrimaryNodeType().getName()).append("\n");
        printMixinNodeTypes(node,printer);
        printNodeProperties(node,printer);
    }

    public static void printNodeProperties(Node node, StringBuilder printer) throws RepositoryException {
        checkPrinter(printer);
        checkNode(node);
        PropertyIterator properties = node.getProperties();
        printer.append("  Properties : \n");
        while (properties.hasNext()) {
            Property property = properties.nextProperty();
            printer.append("    ").append(property.getName()).append(" - ");
            if (property.getDefinition().isMultiple()) {
                for (Value value : property.getValues()) {
                    printer.append(value.getString()).append(" ");
                }
            } else {
                printer.append(property.getString());
            }
            printer.append("\n");
        }
    }

    public static void printMixinNodeTypes(Node node, StringBuilder printer) throws RepositoryException {
        checkPrinter(printer);
        checkNode(node);
        NodeType[] nodeTypes = node.getMixinNodeTypes();
        printer.append("  Mixin node types : ");
        for (NodeType nodeType : nodeTypes) {
            printer.append(nodeType.getName()).append(" ");
        }
        printer.append("\n");
    }

    public static void printVersions(Node node, StringBuilder printer) throws RepositoryException {
        checkPrinter(printer);
        checkNode(node);
        SimpleDateFormat simplDateFormat = new SimpleDateFormat("dd/MMM/yyyy (HH:mm:ss)");

        VersionHistory versionHistory = node.getVersionHistory();
        VersionIterator versionIterator = versionHistory.getAllVersions();

        printer.append("Versions : \n");
        while (versionIterator.hasNext()) {
            Version version = versionIterator.nextVersion();
            printer.append("create date ").append(simplDateFormat.format(version.getCreated().getTime()));
            NodeIterator nodes = version.getNodes();
            while (nodes.hasNext()) {
                Node frozenNode = nodes.nextNode();
                printNode(frozenNode,printer);
            }
        }
    }

    public static void printQueryResults(QueryResult queryResult, StringBuilder printer) throws RepositoryException {
        checkPrinter(printer);
        checkQueryResults(queryResult);
        NodeIterator foundNodes = queryResult.getNodes();
        printer.append("Print found nodes\n");
        while (foundNodes.hasNext()) {
            Node node = foundNodes.nextNode();
            NodePrinter.printNode(node,printer);
        }

    }


    private static void checkPrinter(StringBuilder printer) {
        if (printer == null) {
            throw new IllegalArgumentException("printer cannot be null");
        }
    }

    private static void checkNode(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("node cannot be null");
        }
    }

    private static void checkQueryResults(QueryResult queryResult) {
        if (queryResult == null) {
            throw new IllegalArgumentException("queryResult cannot be null");
        }
    }
}
