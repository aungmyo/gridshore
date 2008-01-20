package nl.gridshore.samples.training.dataaccess.jpa;

import nl.gridshore.samples.training.domain.Employee;
import nl.gridshore.samples.training.dataaccess.EmployeeDao;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 8:20:05 PM
 * Jpa implementation for the Employee data access interface
 */
public class EmployeeDaoJpa extends BaseDaoJpa<Employee> implements EmployeeDao {

    public EmployeeDaoJpa() {
        super(Employee.class, "Employee");
    }

    public Employee findByIdNumber(String idNumber) {
        return null;  //TODO change body of implemented methods use File | Settings | File Templates.
    }
}
