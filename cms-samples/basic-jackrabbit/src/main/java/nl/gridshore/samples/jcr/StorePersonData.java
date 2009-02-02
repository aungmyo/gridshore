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

import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Node;
import java.io.IOException;

/**
 * @author Jettro Coenradie
 *
 * <p>This class is used to demonstrate creating a new node in the repository</p>
 */
public class StorePersonData extends Base {
     public static void main(String[] args) {
        StorePersonData storeData = new StorePersonData();
        storeData.run();
     }

    @Override
    protected void doRun() throws Exception {
        Session session = getSession();

        Node rootNode = session.getRootNode();

        Node node = rootNode.addNode("Jettro","gridshore:person");
        node.setProperty("gridshore:title","Chief Architect");
        node.setProperty("gridshore:email","jettro@jteam.nl");
        node.setProperty("gridshore:age",36);
        Node homeaddress = node.addNode("homeaddress", "gridshore:address");
        homeaddress.setProperty("gridshore:street","Fredriksplein 1");
        homeaddress.setProperty("gridshore:postalcode","1017XK");
        homeaddress.setProperty("gridshore:city","Amsterdam");
        homeaddress.setProperty("gridshore:country","The Netherlands");


        session.save();

        logout(session);
    }
}