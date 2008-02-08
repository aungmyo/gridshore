package nl.gridshore.samples.training.dataaccess.jpa;

import nl.gridshore.samples.training.domain.Employee;
import nl.gridshore.samples.training.dataaccess.EmployeeDao;

import javax.persistence.Query;
import java.util.List;
import java.util.ArrayList;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 8:20:05 PM
 * Jpa implementation for the Employee data access interface
 */
public class EmployeeDaoJpa extends BaseDaoJpa<Employee> implements EmployeeDao {
    private final Logger logger = LoggerFactory.getLogger(EmployeeDaoJpa.class);

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

    public List<Employee> findByExample(Employee exampleEmployee) {
        List<QueryCondition> conditions = new ArrayList<QueryCondition>();
        StringBuilder queryBuiler = new StringBuilder();
        queryBuiler.append("from Employee where ");
        addQueryConditionToList("cell",exampleEmployee.getCell(), conditions, queryBuiler);
        addQueryConditionToList("cluster",exampleEmployee.getCluster(), conditions, queryBuiler);
        addQueryConditionToList("level",exampleEmployee.getLevel(), conditions, queryBuiler);
        addQueryConditionToList("employeeNumber",exampleEmployee.getEmployeeNumber(), conditions, queryBuiler);
        addQueryConditionToList("longName",exampleEmployee.getLongName(), conditions, queryBuiler);

        String queryStr = queryBuiler.toString();
        if (conditions.size() > 0) {
            // remove the last and (3 characters plus 2 spaces)
            queryStr = queryStr.substring(0,queryStr.length()-5);
        } else {
            // no conditions found so remove the where (5 characters plus two spaces)
            queryStr = queryStr.substring(0,queryStr.length()-7);
        }

        logger.debug("Execute the query {}",queryStr);

        Query query = getEntityManager().createQuery(queryStr);
        for(QueryCondition condition : conditions) {
            query.setParameter(condition.getParameter(),condition.getFilterValue());
        }
        //noinspection unchecked
        return query.getResultList();
    }

    private <T> void addQueryConditionToList(String parameter, T filtervalue,List<QueryCondition> conditions, StringBuilder queryBuilder) {
        logger.debug("parameter - value : {} - {}", parameter, filtervalue);
        if (parameter != null && filtervalue != null) {
            if (filtervalue instanceof String) {
                if (!"".equals(filtervalue)) {
                    conditions.add(new QueryCondition<String>(parameter,"%"+filtervalue+"%"));
                    queryBuilder.append(parameter);
                    queryBuilder.append(" like :").append(parameter);
                    queryBuilder.append(" and ");
                }
            } else {
                conditions.add(new QueryCondition<Object>(parameter,filtervalue));
                queryBuilder.append(parameter);
                queryBuilder.append(" = :").append(parameter);
                queryBuilder.append(" and ");
            }
        }
    }

    private class QueryCondition<T> {
        private String parameter;
        private T filterValue;

        private QueryCondition(String parameter, T filterValue) {
            this.parameter = parameter;
            this.filterValue = filterValue;
        }

        public String getParameter() {
            return parameter;
        }

        public T getFilterValue() {
            return filterValue;
        }
    }
}
