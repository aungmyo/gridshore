package nl.gridshore.samples.training.business.impl;

import nl.gridshore.samples.training.business.EmployeeService;
import nl.gridshore.samples.training.domain.Employee;
import nl.gridshore.samples.training.domain.TrainingPlanning;
import nl.gridshore.samples.training.domain.TrainingSession;
import nl.gridshore.samples.training.dataaccess.EmployeeDao;
import nl.gridshore.samples.training.dataaccess.TrainingSessionDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 30, 2008
 * Time: 8:31:31 AM
 * Default implementation for the EmployeeService
 */
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao;
    private TrainingSessionDao trainingSessionDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao, TrainingSessionDao trainingSessionDao) {
        this.employeeDao = employeeDao;
        this.trainingSessionDao = trainingSessionDao;
    }

    public List<Employee> obtainAllEmployees() {
        return employeeDao.loadAll();
    }

    public Employee obtainEmployeeById(Long employeeId) {
        return employeeDao.loadById(employeeId);
    }

    public Employee addTrainingSessionToPlanningOfEmployee(Long employeeId, Long trainingSessionId) {
        Employee employee = employeeDao.loadById(employeeId);
        TrainingSession session = trainingSessionDao.loadById(trainingSessionId);
        TrainingPlanning planning = new TrainingPlanning();
        planning.setSession(session);
        employee.addTrainingPlanning(planning);
        return employee;
    }
}
