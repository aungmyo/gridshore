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
import javax.jcr.NodeIterator;
import javax.jcr.Node;
import javax.jcr.query.QueryManager;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;

/**
 * Class used to demonstrate the search capabilities using XPath
 */
public class SearchData extends Base {

    public static void main(String[] args) {
        SearchData searchData = new SearchData();
        searchData.run();
    }

    @Override
    protected void doRun() throws Exception {
        Session session = getReadonlySession();
        QueryManager queryManager = session.getWorkspace().getQueryManager();
        Query query = queryManager.createQuery("//element(*,nt:unstructured)[jcr:like(@title,'%Chief%')]", Query.XPATH);
        QueryResult queryResult = query.execute();
        NodeIterator foundNodes = queryResult.getNodes();
        System.out.println("Print found nodes");
        while (foundNodes.hasNext()) {
            Node node = foundNodes.nextNode();
            System.out.println("Node : " + node.getName());
        }

        logout(session);
    }
}
