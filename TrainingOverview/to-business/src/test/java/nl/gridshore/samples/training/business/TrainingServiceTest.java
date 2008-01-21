package nl.gridshore.samples.training.business;

import org.junit.Before;
import org.junit.Test;
import nl.gridshore.samples.training.integration.ObtainUpdatedEmployeeDataService;
import nl.gridshore.samples.training.integration.vo.UpdatedEmployeeData;
import nl.gridshore.samples.training.dataaccess.EmployeeDao;
import nl.gridshore.samples.training.dataaccess.ProjectDao;
import nl.gridshore.samples.training.business.impl.TrainingServiceImpl;
import nl.gridshore.samples.training.domain.Employee;
import nl.gridshore.samples.training.domain.Project;
import static org.easymock.EasyMock.*;

import java.util.Set;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 10:33:49 PM
 * Test class for the TrainingService
 */
public class TrainingServiceTest {
    private TrainingService service;

    private ObtainUpdatedEmployeeDataService mockEmpDataService;
    private EmployeeDao mockEmpDao;
    private ProjectDao mockProjectDao;

    @Before
    public void initialize() {
        mockEmpDataService = createMock(ObtainUpdatedEmployeeDataService.class);
        mockEmpDao = createMock(EmployeeDao.class);
        mockProjectDao = createMock(ProjectDao.class);
        service = new TrainingServiceImpl(mockEmpDataService, mockEmpDao, mockProjectDao);
    }

    @Test
    public void importAndHandleEmployeeDataFile_NewEmployeeExistingProject() {
        String dummyFile = "dummy.xls";
        Set<UpdatedEmployeeData> foundEmployees = new HashSet<UpdatedEmployeeData>();
        UpdatedEmployeeData empData1 = createFoundEmployeeData();
        foundEmployees.add(empData1);
        expect(mockEmpDataService.obtainEmployeeData(dummyFile)).andReturn(foundEmployees);
        expect(mockEmpDao.findByIdNumber(empData1.getIdNumber())).andReturn(null);

        Project project = createExistingProject(empData1.getClient());
        expect(mockProjectDao.findProjectByClientAndProject(empData1.getClient(),empData1.getProject())).andReturn(project);

        Employee employee = createReturnedEmployee(empData1);
        project.addEmployee(employee);
        expect(mockEmpDao.save(isA(Employee.class))).andReturn(employee);

        replay(mockEmpDataService, mockEmpDao, mockProjectDao);

        service.importAndHandleEmployeeDataFile(dummyFile);

        verify(mockEmpDataService, mockEmpDao, mockProjectDao);
    }

    @Test
    public void importAndHandleEmployeeDataFile_ExistingEmployeeChangedToExistingProject() {
        String dummyFile = "dummy.xls";
        Set<UpdatedEmployeeData> foundEmployees = new HashSet<UpdatedEmployeeData>();
        UpdatedEmployeeData empData1 = createFoundEmployeeData();
        foundEmployees.add(empData1);
        expect(mockEmpDataService.obtainEmployeeData(dummyFile)).andReturn(foundEmployees);

        Employee employee = createReturnedEmployee(empData1);
        expect(mockEmpDao.findByIdNumber(empData1.getIdNumber())).andReturn(employee);

        Project project = createExistingProject(empData1.getClient());
        expect(mockProjectDao.findProjectByClientAndProject(empData1.getClient(),empData1.getProject())).andReturn(project);

        Employee finalEmployee = createReturnedEmployee(empData1);
        project.addEmployee(finalEmployee);
        expect(mockEmpDao.save(isA(Employee.class))).andReturn(finalEmployee);

        replay(mockEmpDataService, mockEmpDao, mockProjectDao);

        service.importAndHandleEmployeeDataFile(dummyFile);

        verify(mockEmpDataService, mockEmpDao, mockProjectDao);
    }

    @Test
    public void importAndHandleEmployeeDataFile_ExistingEmployeeNonExistingProject() {
        String dummyFile = "dummy.xls";
        Set<UpdatedEmployeeData> foundEmployees = new HashSet<UpdatedEmployeeData>();
        UpdatedEmployeeData empData1 = createFoundEmployeeData();
        foundEmployees.add(empData1);
        expect(mockEmpDataService.obtainEmployeeData(dummyFile)).andReturn(foundEmployees);

        Employee employee = createReturnedEmployee(empData1);
        expect(mockEmpDao.findByIdNumber(empData1.getIdNumber())).andReturn(employee);

        expect(mockProjectDao.findProjectByClientAndProject(empData1.getClient(),empData1.getProject())).andReturn(null);
        Project project = createExistingProject(empData1.getClient());
        Employee employeeFinal = createReturnedEmployee(empData1);
        project.addEmployee(employeeFinal);
        expect(mockProjectDao.save(isA(Project.class))).andReturn(project);
        
        expect(mockEmpDao.save(isA(Employee.class))).andReturn(employee);

        replay(mockEmpDataService, mockEmpDao, mockProjectDao);

        service.importAndHandleEmployeeDataFile(dummyFile);

        verify(mockEmpDataService, mockEmpDao, mockProjectDao);
    }

    private Employee createReturnedEmployee(UpdatedEmployeeData empData1) {
        Employee employee = new Employee();
        employee.setCell(empData1.getCell());
        employee.setCluster(empData1.getCluster());
        employee.setEmployeeNumber(empData1.getIdNumber());
        employee.setLevel(empData1.getLevel());
        employee.setLongName(empData1.getLongName());
        return employee;
    }

    private Project createExistingProject(String client) {
        Project project = new Project();
        project.setId(1L);
        project.setClient(client);
        project.setManagerEmail("d.bruno@accenture.com");
        project.setManagerName("Daniele Bruno");
        project.setName("OCS");
        project.setWbs("ZK1234");
        return project;
    }

    private UpdatedEmployeeData createFoundEmployeeData() {
        UpdatedEmployeeData empData1 = new UpdatedEmployeeData();
        empData1.setCell("Java/Web");
        empData1.setClient("Abnamro");
        empData1.setProject("OCS");
        empData1.setCluster("T");
        empData1.setIdNumber("99994444");
        empData1.setLevel("5. Senior Systems Analyst");
        empData1.setLongName("Coenradies evil twin");
        return empData1;
    }
}
