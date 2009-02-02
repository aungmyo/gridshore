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
        System.out.println("Done !");
    }
}
