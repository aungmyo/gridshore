package nl.gridshore.samples.training.dataaccess.jpa;

import nl.gridshore.samples.training.domain.Employee;
import nl.gridshore.samples.training.dataaccess.EmployeeDao;

import javax.persistence.Query;
import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

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

    /**
     * Need to overload this method to load the lazily loaded collections for plans and wishes
     * @param entityId Long representin the id of the employee to find
     * @return Employee with eagerly fetched collections
     * @throws ObjectRetrievalFailureException Thrown if the id that was provided is unknown
     */
    public Employee loadById(Long entityId) throws ObjectRetrievalFailureException {
        Employee emp = super.loadById(entityId);
        emp.getTrainingPlans().size();
        emp.getTrainingWishes().size();
        return emp;
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
