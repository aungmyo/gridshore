package nl.gridshore.samples.training.dataaccess;

import org.springframework.test.jpa.AbstractJpaTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.junit.runner.RunWith;
import org.junit.Test;
import nl.gridshore.samples.training.domain.Employee;

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

}
