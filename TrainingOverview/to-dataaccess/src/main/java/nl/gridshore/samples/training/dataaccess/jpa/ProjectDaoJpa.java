package nl.gridshore.samples.training.dataaccess.jpa;

import nl.gridshore.samples.training.domain.Project;
import nl.gridshore.samples.training.dataaccess.ProjectDao;

import javax.persistence.Query;
import java.util.List;

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

    public Project findProjectByClientAndProject(String client, String project) {
        Query query = getEntityManager().createQuery(
                "select p from Project as p where p.name like :name and p.client like :client");
        query.setParameter("name", project);
        query.setParameter("client", client);
        //noinspection unchecked
        List<Project> projects = query.getResultList();
        Project foundProject = null;
        if (projects.size() > 0) {
            foundProject = projects.get(0);
        }
        return foundProject;
    }
}
