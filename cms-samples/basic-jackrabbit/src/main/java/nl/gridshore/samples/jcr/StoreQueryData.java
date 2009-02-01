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

import javax.jcr.Session;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.query.QueryManager;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;

/**
 * <p>Class to demonstrate the usage of storing a query in a node</p>
 *
 * @author jettro coenradie
 *         Date: Feb 1, 2009
 */
public class StoreQueryData extends Base {

    public static void main(String[] args) {
        StoreQueryData queryData = new StoreQueryData();
        queryData.run();
    }

    @Override
    protected void doRun() throws Exception {
        StringBuilder printer = new StringBuilder();

        storeQueryInRepository();

        Session readonlySession = getReadonlySession();

        Query storedQuery = obtainQueryFromRepository(printer, readonlySession);

        QueryResult queryResult = storedQuery.execute();

        NodePrinter.printQueryResults(queryResult,printer);

        logout(readonlySession);

        System.out.println(printer);
    }

    private void storeQueryInRepository() throws RepositoryException {
        Session session = getSession();
        QueryManager queryManager = session.getWorkspace().getQueryManager();
        Query query = queryManager.createQuery(
                "//element(*,nt:unstructured)[jcr:like(@title,'%Chief%')]", Query.XPATH);
        query.storeAsNode("/SearchChiefs");
        session.save();
        logout(session);
    }

    private Query obtainQueryFromRepository(StringBuilder printer, Session readonlySession) throws RepositoryException {
        Node root = readonlySession.getRootNode();
        Node storedQueryNode = root.getNode("SearchChiefs");
        NodePrinter.printNode(storedQueryNode,printer);

        QueryManager queryManager = readonlySession.getWorkspace().getQueryManager();
        return queryManager.getQuery(storedQueryNode);
    }

}
