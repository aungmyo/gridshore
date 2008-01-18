package nl.gridshore.samples.training.integration;

import nl.gridshore.samples.training.integration.vo.UpdatedEmployeeData;
import nl.gridshore.samples.training.integration.exceptions.IntegrationException;

import java.util.Set;
import java.io.File;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 8:26:20 PM
 * Service that takes care of obtaining new Employee data and creating objects that can be used to do the real update.
 */
public interface ObtainUpdatedEmployeeDataService {

    /**
     * Obtain a set with data objects containing the contents of the file as provided by the path
     * @param filenameIncludingPath Path to the file to load
     * @return Set containing UpdatedEmployeeData objects containing the contents of the loaded file.
     * @throws nl.gridshore.samples.training.integration.exceptions.IntegrationException generic exception for this component
     * thrown if something goes wrong. Can be a more specific exception.
     */
    Set<UpdatedEmployeeData> obtainEmployeeData(String filenameIncludingPath) throws IntegrationException;

    /**
     * Obtain a set with data objects containing the contents of the provided file
     * @param file File provided to read the data from
     * @return Set containing UpdatedEmployeeData objects containing the contents of the file.
     * @throws nl.gridshore.samples.training.integration.exceptions.IntegrationException generic exception for this component
     * thrown if something goes wrong. Can be a more specific exception.
     */
    Set<UpdatedEmployeeData> obtainEmployeeData(File file) throws IntegrationException;

    /**
     * Obtain a set with data objects containing the contents of the provided input stream. The normal flow does not
     * close the stream. This way you can re-use it if you want.
     * @param inputStream InputStream used to read the new data from. The stream is not closed.
     * @return Set containing UpdatedEmployeeData objects containing the contents of the input stream.
     * @throws nl.gridshore.samples.training.integration.exceptions.IntegrationException generic exception for this component
     * thrown if something goes wrong. Can be a more specific exception.
     */
    Set<UpdatedEmployeeData> obtainEmployeeData(InputStream inputStream) throws IntegrationException;
}
