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
     * Returns the project from the provided client, if no client/project match can be found a null is returned
     * @param client String representing the client to look for
     * @param project String representing the project to look for
     * @return The found Project or a null
     */
    Project findProjectByClientAndProject(String client, String project);
}
