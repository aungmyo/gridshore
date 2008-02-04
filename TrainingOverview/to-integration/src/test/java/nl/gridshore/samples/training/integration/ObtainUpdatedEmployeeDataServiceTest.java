package nl.gridshore.samples.training.integration;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import nl.gridshore.samples.training.integration.vo.UpdatedEmployeeData;
import nl.gridshore.samples.training.integration.impl.ObtainUpdatedEmployeeDataServiceImpl;
import nl.gridshore.samples.training.integration.exceptions.IntegrationInputDataException;
import nl.gridshore.samples.training.integration.exceptions.IntegrationException;

import java.io.*;
import java.util.Set;

import nl.gridshore.samples.training.integration.ObtainUpdatedEmployeeDataService;

/**
 * ObtainUpdatedEmployeeDataServiceImpl Tester.
 *
 * @since <pre>01/18/2008</pre>
 * @version 1.0
 */
public class ObtainUpdatedEmployeeDataServiceTest {
    private static final String TEST_INPUT_FILE_NAME = "src/test/resources/example.xls";
    private ObtainUpdatedEmployeeDataService service;

    @Before
    public void initialize() {
        service = new ObtainUpdatedEmployeeDataServiceImpl();
    }
    
    @After
    public void cleanup() {
        service = null;
    }

//    @Test
    public void obtainEmployeeDataByString() {
        Set<UpdatedEmployeeData> updatedEmployees = service.obtainEmployeeData(TEST_INPUT_FILE_NAME);

        executeGenericAssertsOnUpdatedEmployees(updatedEmployees);
    }

    @Test (expected = IntegrationInputDataException.class)
    public void obtainEmployeeDataByStringNonExistingFile() {
        service.obtainEmployeeData("Idonotexist");
    }

//    @Test
    public void obtainEmployeeDataByFile() {
        File file = new File(TEST_INPUT_FILE_NAME);
        Set<UpdatedEmployeeData> updatedEmployees = service.obtainEmployeeData(file);
        executeGenericAssertsOnUpdatedEmployees(updatedEmployees);
    }

    @Test (expected = IntegrationInputDataException.class)
    public void obtainEmployeeDataByFileWithNonExistingPath() {
        File file = new File("IdoNotExist");
        service.obtainEmployeeData(file);
    }

//    @Test
    public void obtainEmployeeDataByInputStream() throws IOException {
        File file = new File(TEST_INPUT_FILE_NAME);
        InputStream in = new FileInputStream(file);
        Set<UpdatedEmployeeData> updatedEmployees = service.obtainEmployeeData(in);
        in.close();

        executeGenericAssertsOnUpdatedEmployees(updatedEmployees);
    }

//    @Test (expected = IntegrationException.class)
    public void obtainEmployeeDataByInputStreamExceptionpath() throws IOException {
        File file = new File(TEST_INPUT_FILE_NAME);
        InputStream in = new FileInputStream(file);
        in.close(); // makes sure we get an error when trying to use it
        service.obtainEmployeeData(in);

    }

    private void executeGenericAssertsOnUpdatedEmployees(Set<UpdatedEmployeeData> updatedEmployees) {
        assertEquals("Number of found employee data objects is not as expected", 635,updatedEmployees.size());

        UpdatedEmployeeData empData = null;
        for(UpdatedEmployeeData curEmp : updatedEmployees) {
            if ("10091373".equals(curEmp.getIdNumber())) {
                empData = curEmp;
                break;
            }
        }
        assertNotNull("The employee with id 10091373 should have been found",empData);
        assertEquals("idnumber is not as expected", "10091373",empData.getIdNumber());
        assertEquals("long name is not as expected", "Bout, Kees", empData.getLongName());
        assertEquals("level is not as expected", "6. Principal Consultant", empData.getLevel());
        assertEquals("cell is not as expeced", "Oracle ERP", empData.getCell());
        assertEquals("cluster is not as expected", "OS", empData.getCluster());
        assertEquals("client is not as expected", "Cargill", empData.getClient());
        assertEquals("client is not as expected", "A Force RC", empData.getProject());
    }
}
