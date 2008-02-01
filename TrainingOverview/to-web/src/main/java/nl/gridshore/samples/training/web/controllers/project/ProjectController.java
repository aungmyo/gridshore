package nl.gridshore.samples.training.web.controllers.project;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import nl.gridshore.samples.training.business.ProjectService;
import nl.gridshore.samples.training.domain.Employee;
import nl.gridshore.samples.training.domain.Project;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 21, 2008
 * Time: 11:18:40 PM
 * Controller class to handle all project related requests that do not really change data
 */
@Controller
public class ProjectController {
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping ("/projects.view")
    public ModelMap projectsHandler() {
        return new ModelMap("projectsList", projectService.obtainAllProjects());
    }

    @RequestMapping ("/projectemployees.view")
    public ModelMap projectEmployeesHandler(@RequestParam("projectId")long projectId) {
        Project project = projectService.obtainProjectById(projectId);
        return new ModelMap(project);
    }
}
