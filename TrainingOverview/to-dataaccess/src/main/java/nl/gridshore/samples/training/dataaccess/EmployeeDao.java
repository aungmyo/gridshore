package nl.gridshore.samples.training.dataaccess;

import nl.gridshore.samples.training.domain.Employee;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 8:19:24 PM
 * Data access definition for all Employee instances
 */
public interface EmployeeDao extends BaseDao<Employee>{
    /**
     * Executed a query in the data repository for an employee with the provided id number. If the id number is not found,
     * null is returned.
     * @param idNumber String representing the id number to look for
     * @return Employee belonging to the id number provided or null if it could not be found.
     */
    Employee findByIdNumber(String idNumber);
}
