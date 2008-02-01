package nl.gridshore.samples.training.business;

import nl.gridshore.samples.training.domain.Project;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 10:15:01 PM
 * Business service containing all methods that are project related
 */
public interface ProjectService {
    /**
     * Imports the contents of the provided filename. The filename must have a very specific format making this
     * import functionality bound to the format of this excel document.
     * @param filenameAndLocation String containing the filename to import
     */
    void importAndHandleEmployeeDataFile(String filenameAndLocation);

    /**
     * Returns all the projects that are available
     * @return List of pojects
     */
    List<Project> obtainAllProjects();

    /**
     * Retrieves the project by Id and throws an exception if it cannot be found
     * @param projectId Long representing the id of the project to load
     * @return Project belonging to the id
     */
    Project obtainProjectById(Long projectId);

    /**
     * Store the provided project in the database
     * @param project Project to be stored
     */
    void storeProject(Project project);
}
