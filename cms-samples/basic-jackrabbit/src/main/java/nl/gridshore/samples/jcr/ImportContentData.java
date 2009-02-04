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
import javax.jcr.ImportUUIDBehavior;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 2, 2009
 * Time: 11:36:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class ImportContentData extends Base {

    public static void main(String[] args) {
        ImportContentData importContentData = new ImportContentData();
        importContentData.run();
    }

    @Override
    protected void doRun() throws Exception {
        Session session = getSession();
        InputStream asStream = this.getClass().getClassLoader().getResourceAsStream("content/gridshore_node_jettro.xml");
        session.importXML("/",asStream, ImportUUIDBehavior.IMPORT_UUID_CREATE_NEW);

        session.save();

        logout(session);
        System.out.println("Done !");
    }
}
