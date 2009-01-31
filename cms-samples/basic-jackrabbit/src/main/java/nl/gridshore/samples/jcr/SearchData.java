package nl.gridshore.samples.jcr;

import javax.jcr.Session;
import javax.jcr.NodeIterator;
import javax.jcr.Node;
import javax.jcr.query.QueryManager;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 30, 2009
 * Time: 2:23:03 PM
 * To change this template use File | Settings | File Templates.
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
        Query query = queryManager.createQuery("//element(*,nt:unstructured)[jcr:like(title,'%title%')", Query.XPATH);
        QueryResult queryResult = query.execute();
        NodeIterator foundNodes = queryResult.getNodes();
        System.out.println("Print found nodes");
        while (foundNodes.hasNext()) {
            Node node = foundNodes.nextNode();
            System.out.println("Node : " + node.getName() + " uuid : " + node.getUUID());
        }

        logout(session);
    }
}
