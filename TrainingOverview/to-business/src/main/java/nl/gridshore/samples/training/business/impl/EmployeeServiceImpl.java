package nl.gridshore.samples.training.business.impl;

import nl.gridshore.samples.training.business.EmployeeService;
import nl.gridshore.samples.training.domain.*;
import nl.gridshore.samples.training.dataaccess.EmployeeDao;
import nl.gridshore.samples.training.dataaccess.TrainingSessionDao;
import nl.gridshore.samples.training.dataaccess.TrainingDao;

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
    private TrainingDao trainingDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao, TrainingSessionDao trainingSessionDao, TrainingDao trainingDao) {
        this.employeeDao = employeeDao;
        this.trainingSessionDao = trainingSessionDao;
        this.trainingDao = trainingDao;
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

    public Employee addTrainingToWishListOfEmployee(Long employeeId, Long trainingId) {
        Employee employee = employeeDao.loadById(employeeId);
        Training training = trainingDao.loadById(trainingId);
        TrainingStatus wish = new TrainingStatus();
        wish.setTraining(training);
        employee.addTraningsWishes(wish);
        return employee;
    }

    public List<Employee> searchEmployees(Employee searchEmployee) {
        return employeeDao.findByExample(searchEmployee);
    }
}
