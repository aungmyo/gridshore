package nl.gridshore.samples.training.dataaccess;

import nl.gridshore.samples.training.domain.Project;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 6:08:05 PM
 * Interface definition for data access realted to Project instances
 */
public interface ProjectDao extends BaseDao<Project> {
    /**
     * Returns a project from the provided client, if multiple projects exist, only the first is choosen. if no client
     * or projects are found a null is returned
     * @param client String representing the client to look for a project
     * @return The found Project or a null
     */
    Project findProjectByClient(String client);
}
