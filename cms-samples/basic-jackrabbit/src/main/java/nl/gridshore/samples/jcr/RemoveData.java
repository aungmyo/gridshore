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
import javax.jcr.Node;

/**
 * <p>Class to demonstrate removing data.</p>
 *
 * @author jettro coenradie
 *         Date: Jan 31, 2009
 */
public class RemoveData extends Base {
    public static void main(String[] args) {
        RemoveData removeDate = new RemoveData();
        removeDate.run();
    }

    @Override
    protected void doRun() throws Exception {
        Session session = getSession();
        Node rootNode = session.getRootNode();
        Node foundNode = rootNode.getNode("Jettro");

        foundNode.remove();

        session.save();

        logout(session);
    }
}
