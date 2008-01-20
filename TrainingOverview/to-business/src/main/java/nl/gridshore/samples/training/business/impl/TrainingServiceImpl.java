package nl.gridshore.samples.training.business.impl;

import nl.gridshore.samples.training.business.TrainingService;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 10:28:14 PM
 * Implementation for the TrainingService interface
 */
public class TrainingServiceImpl implements TrainingService {
    final Logger logger = LoggerFactory.getLogger(TrainingServiceImpl.class);

    private ObtainUpdatedEmployeeDataService empDataService;
    private EmployeeDao employeeDao;
    private ProjectDao projectDao;

    public TrainingServiceImpl(ObtainUpdatedEmployeeDataService empDataService, EmployeeDao employeeDao, ProjectDao projectDao) {
        this.empDataService = empDataService;
        this.employeeDao = employeeDao;
        this.projectDao = projectDao;
    }

    public void importAndHandleEmployeeDataFile(String filenameAndLocation) {
        Set<UpdatedEmployeeData> foundEmps = null;
        try {
            foundEmps = empDataService.obtainEmployeeData(filenameAndLocation);
        } catch (IntegrationException e) {
            logger.error("The file with name {} could not be found, or something went wrong while loading",filenameAndLocation);
            throw new ImportEmployeeDataException(BusinessExceptionCodes.ERROR_IMPORTING_NEW_EMPLOYEE_DATA,e);
        }

        for(UpdatedEmployeeData foundEmp : foundEmps) {
            Employee existingEmployee = employeeDao.findByIdNumber(foundEmp.getIdNumber());
            if (existingEmployee != null) {
                // TODO : implement this as well
                logger.warn("Existing employee with id {}",foundEmp.getIdNumber());
            } else {
                Employee emp = new Employee();
                emp.setCell(foundEmp.getCell());
                emp.setCluster(foundEmp.getCluster());
                emp.setEmployeeNumber(foundEmp.getIdNumber());
                emp.setLevel(foundEmp.getLevel());
                emp.setLongName(foundEmp.getLongName());
                Project foundProject = projectDao.findProjectByClient(foundEmp.getClient());
                if (foundProject != null) {
                    foundProject.addEmployee(emp);
                } else {
                    // TODO : create some kind of problem service
                    logger.warn("Could not find project for client {}", foundEmp.getClient());
                }
                employeeDao.save(emp);
            }
        }
    }

}
