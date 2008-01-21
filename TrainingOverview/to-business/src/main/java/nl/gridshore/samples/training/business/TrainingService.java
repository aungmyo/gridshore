package nl.gridshore.samples.training.business;

import nl.gridshore.samples.training.domain.Project;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 10:15:01 PM
 * Business service containing all methods that are training related
 */
public interface TrainingService {
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
}
