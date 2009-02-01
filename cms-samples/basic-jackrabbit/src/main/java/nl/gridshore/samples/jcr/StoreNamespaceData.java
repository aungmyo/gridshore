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
 * <p></p>
 *
 * @author jettro coenradie
 *         Date: Feb 1, 2009
 */
public class StoreNamespaceData extends Base {

    public static void main(String[] args) {
        StoreNamespaceData storeNamespaceData = new StoreNamespaceData();
        storeNamespaceData.run();
    }

    @Override
    protected void doRun() throws Exception {
        Session session = getSession();
        NamespaceRegistry namespaceRegistry = session.getWorkspace().getNamespaceRegistry();

        namespaceRegistry.registerNamespace("gridshore","http://www.gridshore.nl/");

        logout(session);
    }
}
