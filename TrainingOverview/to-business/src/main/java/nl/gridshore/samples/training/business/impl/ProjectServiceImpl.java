package nl.gridshore.samples.training.business.impl;

import nl.gridshore.samples.training.business.ProjectService;
import nl.gridshore.samples.training.business.exceptions.ImportEmployeeDataException;
import nl.gridshore.samples.training.business.exceptions.BusinessExceptionCodes;
import nl.gridshore.samples.training.integration.ObtainUpdatedEmployeeDataService;
import nl.gridshore.samples.training.integration.exceptions.IntegrationException;
import nl.gridshore.samples.training.integration.vo.UpdatedEmployeeData;
import nl.gridshore.samples.training.dataaccess.EmployeeDao;
import nl.gridshore.samples.training.dataaccess.ProjectDao;
import nl.gridshore.samples.training.domain.Employee;
import nl.gridshore.samples.training.domain.Project;

import java.util.Set;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 10:28:14 PM
 * Implementation for the ProjectService interface
 */
public class ProjectServiceImpl implements ProjectService {
    final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private ObtainUpdatedEmployeeDataService empDataService;
    private EmployeeDao employeeDao;
    private ProjectDao projectDao;

    @Autowired
    public ProjectServiceImpl(ObtainUpdatedEmployeeDataService empDataService, EmployeeDao employeeDao, ProjectDao projectDao) {
        this.empDataService = empDataService;
        this.employeeDao = employeeDao;
        this.projectDao = projectDao;
    }

    public void importAndHandleEmployeeDataFile(String filenameAndLocation) {
        Set<UpdatedEmployeeData> foundEmps;
        try {
            foundEmps = empDataService.obtainEmployeeData(filenameAndLocation);
        } catch (IntegrationException e) {
            logger.error("The file with name {} could not be found, or something went wrong while loading",filenameAndLocation);
            throw new ImportEmployeeDataException(BusinessExceptionCodes.ERROR_IMPORTING_NEW_EMPLOYEE_DATA,e);
        }

        for(UpdatedEmployeeData foundEmp : foundEmps) {
            insertOrUpdateObtainedEmployeeData(foundEmp);
        }
    }

    public List<Project> obtainAllProjects() {
        return projectDao.loadAll();
    }

    public Project obtainProjectById(Long projectId) {
        return projectDao.loadById(projectId);
    }

    public void storeProject(Project project) {
        projectDao.save(project);
    }

    private void insertOrUpdateObtainedEmployeeData(UpdatedEmployeeData foundEmp) {
        Employee existingEmployee = employeeDao.findByIdNumber(foundEmp.getIdNumber());
        if (existingEmployee != null) {
            logger.info("Existing employee with id {}",foundEmp.getIdNumber());
            fillEmployeeWithObtainedData(existingEmployee,foundEmp);
            if (projectOfEmployeeIsChanged(foundEmp, existingEmployee)) {
                if (foundEmp.getClient() == null) {
                    existingEmployee.getProject().removeEmployee(existingEmployee);
                    existingEmployee.setProject(null);
                } else {
                    fillProjectForProvidedEmployee(foundEmp,existingEmployee);
                }
            }
            employeeDao.save(existingEmployee);
        } else {
            Employee emp = new Employee();
            fillEmployeeWithObtainedData(emp,foundEmp);
            fillProjectForProvidedEmployee(foundEmp, emp);
            employeeDao.save(emp);
        }
    }

    private void fillProjectForProvidedEmployee(UpdatedEmployeeData foundEmp, Employee emp) {
        Project foundProject = projectDao.findProjectByClientAndProject(foundEmp.getClient(), foundEmp.getProject());
        if (foundProject != null) {
            foundProject.addEmployee(emp);
        } else {
            logger.warn("Could not find project for client {} and project {}", new String[] {foundEmp.getClient(), foundEmp.getProject()});
            Project project = new Project();
            project.setClient(foundEmp.getClient());
            project.setName(foundEmp.getProject());
            project = projectDao.save(project);
            project.addEmployee(emp);
        }
    }

    private boolean projectOfEmployeeIsChanged(UpdatedEmployeeData foundEmp, Employee existingEmployee) {
        boolean changeOfProject = false;
        boolean nullClient = (foundEmp.getClient() == null);
        boolean nullExistingProject = (existingEmployee.getProject() == null);

        // if new client is null project is null as well, e can have a client without a project
        if (!(nullClient && nullExistingProject)) {
            if (!nullExistingProject && !(
                    existingEmployee.getProject().getClient().equals(foundEmp.getClient())
                            || existingEmployee.getProject().getName().equals(foundEmp.getProject())
                                            )
                ) {
                changeOfProject = true;
            } else if(nullExistingProject && !nullClient) {
                changeOfProject = true;
            }
        }
        return changeOfProject;
    }

    private void fillEmployeeWithObtainedData(Employee emp, UpdatedEmployeeData foundEmp) {
        emp.setCell(foundEmp.getCell());
        emp.setCluster(foundEmp.getCluster());
        emp.setEmployeeNumber(foundEmp.getIdNumber());
        emp.setLevel(foundEmp.getLevel());
        emp.setLongName(foundEmp.getLongName());
    }

}
