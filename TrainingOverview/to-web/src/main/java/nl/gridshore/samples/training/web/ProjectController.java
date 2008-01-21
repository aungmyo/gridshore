package nl.gridshore.samples.training.web;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import nl.gridshore.samples.training.business.TrainingService;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 21, 2008
 * Time: 11:18:40 PM
 * Controller class to handle all project related requests that do not really change data
 */
@Controller
public class ProjectController {
    private TrainingService trainingService;

    @Autowired
    public ProjectController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @RequestMapping ("/projects.view")
    public ModelMap projectsHandler() {
        return new ModelMap("projectsList",trainingService.obtainAllProjects());
    }
}
