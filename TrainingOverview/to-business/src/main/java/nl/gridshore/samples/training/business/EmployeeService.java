package nl.gridshore.samples.training.business;

import nl.gridshore.samples.training.domain.Employee;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 30, 2008
 * Time: 8:29:45 AM
 * All services that are employee related, or better, from the employee perspective
 */
public interface EmployeeService {

    /**
     * Returns a list with all availble employees
     * @return List of all employees
     */
    List<Employee> obtainAllEmployees();

    /**
     * Returns the employee belonging to the provided id
     * @param employeeId Long representing the id of the employee to be found
     * @return Employee found
     */
    Employee obtainEmployeeById(Long employeeId);

    /**
     * Adds the training session belonging to the provided session id to the employee with the provided id.
     * @param employeeId Employee to add the Training session to
     * @param trainingSessionId TrainingSession to be found
     * @return Employee with the TrainingSession added
     */
    Employee addTrainingSessionToPlanningOfEmployee(Long employeeId, Long trainingSessionId);

    /**
     * Add the training beonging to the provided id to the wish list of the employee
     * @param employeeId Long representing the id of the employee to be found
     * @param trainingId LOng representing the id of the training to be found
     * @return Employee with the Training added to his wish list
     */
    Employee addTrainingToWishListOfEmployee(Long employeeId, Long trainingId);

    /**
     * Returns a list with all the employees filtered based on the provided Employee instance
     * @param searchEmployee Employee containing the data to filter on
     * @return List of Employees that comply to the provided data
     */
    List<Employee> searchEmployees(Employee searchEmployee);
}
