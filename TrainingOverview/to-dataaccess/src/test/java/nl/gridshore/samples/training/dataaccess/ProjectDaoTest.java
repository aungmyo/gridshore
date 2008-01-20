package nl.gridshore.samples.training.dataaccess;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.jpa.AbstractJpaTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.ObjectRetrievalFailureException;
import nl.gridshore.samples.training.domain.Project;
import nl.gridshore.samples.training.domain.Employee;
import nl.gridshore.samples.training.dataaccess.testhelp.DatabaseOperations;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 6:10:36 PM
 * Test class for the ProjectDao implementation.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-dataaccess-config.xml","classpath:dataaccess-config.xml"})
public class ProjectDaoTest extends AbstractJpaTests {
    private ProjectDao projectDao;

    @Autowired
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Test
    @Transactional(readOnly = true)
    public void loadAllProjects() {
        assertEquals("The total number of projects is not as expected",2,projectDao.loadAll().size());
    }

    @Test
    @Transactional
    public void saveNewProject() {
        int numBefore = projectDao.loadAll().size();
        Project project = createDummyNewProject();

        projectDao.save(project);

        assertEquals("NUmber of projects is not increased like it should after a save of a new object",
                numBefore+1, projectDao.loadAll().size());
    }

    @Test
    @Transactional
    public void saveNewProjectWithNonExsitingEmployee() {
        // TODO jettro 20/01/2008 : I expected an error here with a non existing employee
        Project project = createDummyNewProject();
        Employee emp = new Employee();
        emp.setCell("test");
        emp.setCluster("clust");
        emp.setEmployeeNumber("12345678");
        emp.setLevel("2. senior programmer");
        emp.setLongName("I am test");
        project.addEmployee(emp);

        projectDao.save(project);
    }

    @Test
    @Transactional (readOnly = true)
    public void loadProjectById() {
        Project project = projectDao.loadById((long) DatabaseOperations.PROJECT_COMPLETE_ID);
        assertEquals("project name is not as expected","OCS",project.getName());
        assertEquals("There should be exactly one employee on the project", 1, project.getEmployees().size());
    }

    @Test (expected = ObjectRetrievalFailureException.class)
    @Transactional (readOnly = true)
    public void loadProjectBNonExistingId() {
        projectDao.loadById(999L);
    }

    private Project createDummyNewProject() {
        Project project = new Project();
        project.setClient("Accenture");
        project.setManagerEmail("jettro.coenradie@accenture.com");
        project.setManagerName("COenradie, Jettro");
        project.setName("ADSJ");
        project.setWbs("notneeded");
        return project;
    }


}
