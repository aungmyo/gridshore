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

import org.apache.jackrabbit.api.JackrabbitNodeTypeManager;

import javax.jcr.Session;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 2, 2009
 * Time: 9:52:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class ImportCustomNodeTypeData extends Base {

    public static void main(String[] args) {
        ImportCustomNodeTypeData importCustomNodeTypeData = new ImportCustomNodeTypeData();
        importCustomNodeTypeData.run();
    }

    @Override
    protected void doRun() throws Exception {
        Session session = getSession();
        JackrabbitNodeTypeManager typeManager = (JackrabbitNodeTypeManager) session.getWorkspace().getNodeTypeManager();
        typeManager.registerNodeTypes(this.getClass().getClassLoader().getResourceAsStream("types/customnodetypes.cnd"),
                JackrabbitNodeTypeManager.TEXT_X_JCR_CND);
        logout(session);
        System.out.println("Done ! ");
    }
}
