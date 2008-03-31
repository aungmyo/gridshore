package nl.gridshore.samples.training.dataaccess;

import org.springframework.test.jpa.AbstractJpaTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.junit.runner.RunWith;
import org.junit.Test;
import nl.gridshore.samples.training.domain.Employee;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 8:25:11 PM
 * test class for the EmployeeDao instance
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-dataaccess-config.xml","classpath:dataaccess-config.xml"})
public class EmployeeDaoTest extends AbstractJpaTests {
    private EmployeeDao employeeDao;

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Test
    @Transactional(readOnly = true)
    public void loadAllEmployees() {
        assertEquals("The total number of employees is not as expected",2,employeeDao.loadAll().size());
    }

    @Test
    @Transactional
    public void saveNewProjectWithNonExsitingEmployee() {
        int numBefore = employeeDao.loadAll().size();

        Employee emp = new Employee();
        emp.setCell("test");
        emp.setCluster("clust");
        emp.setEmployeeNumber("12345678");
        emp.setLevel("2. senior programmer");
        emp.setLongName("I am test");

        employeeDao.save(emp);

        assertEquals("number of employees after save is not as expected",numBefore+1,employeeDao.loadAll().size());
    }

    @Test
    @Transactional(readOnly = true)
    public void loadEmployeeByIdNumber() {
        assertNotNull("an employee should have been found", employeeDao.findByIdNumber("12345678"));
    }

    @Test
    @Transactional(readOnly = true)
    public void loadEmployeeByIdNumberNonExisting() {
        assertNull("no employee should have been found", employeeDao.findByIdNumber("10000000"));
    }

    @Test
    @Transactional(readOnly = true)
    public void findByExample() {
        Employee employee = new Employee();
        employee.setCell("Java/Web");
        List<Employee> employees = employeeDao.findByExample(employee);
        assertNotNull("one employee should have been found",employees);
        assertEquals("Exactly one employee should have been found",1,employees.size());
    }

    @Test
    @Transactional(readOnly = true)
    public void findByEmptyExample() {
        Employee employee = new Employee();
        List<Employee> employees = employeeDao.findByExample(employee);
        assertNotNull("employees should have been found",employees);
        assertEquals("Exactly two employees should have been found with an empty employee as filter",
                2, employees.size());
    }

    @Test
    @Transactional(readOnly = true)
    public void findByExampleTwoArgs() {
        Employee employee = new Employee();
        employee.setCell("Java/Web");
        employee.setCluster("T");
        List<Employee> employees = employeeDao.findByExample(employee);
        assertNotNull("one employee should have been found",employees);
        assertEquals("Exactly one employee should have been found",1,employees.size());
    }

    @Test
    @Transactional(readOnly = true)
    public void findByExampleTwoArgsNoResults() {
        Employee employee = new Employee();
        employee.setCell("Java/Web");
        employee.setCluster("B"); // non existing cluster
        List<Employee> employees = employeeDao.findByExample(employee);
        assertNotNull("an empty list of employees should have been returned",employees);
        assertEquals("no employees should have been found",0,employees.size());
    }

}
