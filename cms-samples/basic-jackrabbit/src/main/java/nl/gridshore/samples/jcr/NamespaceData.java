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
import javax.jcr.NamespaceRegistry;

/**
 * <p>Class to demonstrate the use of namespaces</p>
 *
 * @author jettro coenradie
 *         Date: Jan 31, 2009
 */
public class NamespaceData extends Base {
    public static void main(String[] args) {
        NamespaceData namespaceData = new NamespaceData();
        namespaceData.run();
    }

    @Override
    protected void doRun() throws Exception {
        Session session = getReadonlySession();
        NamespaceRegistry namespaceRegistry = session.getWorkspace().getNamespaceRegistry();
        StringBuilder printer = new StringBuilder();
        printer.append("Namespaces : \n");
        for (String namespace :namespaceRegistry.getPrefixes()) {
            printer.append(namespace).append(" - ").append(namespaceRegistry.getURI(namespace)).append("\n");
        }

        System.out.println(printer);
        
        logout(session);
    }
}
