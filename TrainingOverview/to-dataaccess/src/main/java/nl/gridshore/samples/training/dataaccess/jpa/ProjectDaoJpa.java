package nl.gridshore.samples.training.dataaccess.jpa;

import nl.gridshore.samples.training.domain.Project;
import nl.gridshore.samples.training.dataaccess.ProjectDao;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 6:09:04 PM
 * Jpa implementation for the data access of all Projects
 */
public class ProjectDaoJpa extends BaseDaoJpa<Project> implements ProjectDao {
    public ProjectDaoJpa() {
        super(Project.class, "Project");
    }

}
