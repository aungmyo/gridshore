package nl.gridshore.samples.training.dataaccess.jpa;

import nl.gridshore.samples.training.domain.Employee;
import nl.gridshore.samples.training.dataaccess.EmployeeDao;

import javax.persistence.Query;
import java.util.List;

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
        Query query = getEntityManager().createQuery(
                "select e from Employee as e where e.employeeNumber like :empnumber");
        query.setParameter("empnumber", idNumber);
        //noinspection unchecked
        List<Employee> employees = query.getResultList();
        Employee foundEmp = null;
        if (employees.size() > 0) {
            foundEmp = employees.get(0);
        }
        return foundEmp;
    }
}
