package nl.gridshore.samples.training.web.controllers.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import nl.gridshore.samples.training.business.ProjectService;
import nl.gridshore.samples.training.domain.Project;
import nl.gridshore.samples.training.web.controllers.ControllerConstants;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 23, 2008
 * Time: 4:46:44 PM
 * Controller class for editing a Project instance
 */
@Controller
@RequestMapping("/editproject.view")
@SessionAttributes("project")
public class EditProjectController {
    private static final String REDIRECT_PROJECTS_VIEW = "redirect:projects.view";
    private static final String VIEW_NAME_FORM = "projectForm";
    private static final String SESSION_OBJECT_NAME = "project";

    private ProjectService projectService;

    @Autowired
    public EditProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@RequestParam("projectId") long projectId, ModelMap modelMap) {
        Project project = projectService.obtainProjectById(projectId);
        modelMap.addAttribute(project);
        return VIEW_NAME_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processFormSubmit(
            @RequestParam(value = ControllerConstants.REQUEST_PARAM_CANCEL, required = false) String cancel,
            @ModelAttribute(SESSION_OBJECT_NAME)Project project, BindingResult bindingResult, SessionStatus status) {
        String returnValue;
        if (ControllerConstants.REQUEST_PARAM_CANCEL_VALUE.equals(cancel)) {
            returnValue = REDIRECT_PROJECTS_VIEW;
        } else if (bindingResult.hasErrors()) {
            returnValue = VIEW_NAME_FORM;
        } else {
            projectService.storeProject(project);
            status.setComplete();
            returnValue = REDIRECT_PROJECTS_VIEW;
        }
        return returnValue;
    }

}
