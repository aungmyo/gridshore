package nl.gridshore.samples.training.web.controllers.training;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import nl.gridshore.samples.training.business.TrainingService;
import nl.gridshore.samples.training.domain.TrainingSession;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 27, 2008
 * Time: 5:33:42 PM
 * Update a session
 */
@Controller
@RequestMapping("/edittrainingsession.view")
@SessionAttributes("trainingSession")
public class EditTrainingSessionController extends UpdateableTrainingSessionController {

    @Autowired
    public EditTrainingSessionController(TrainingService trainingService, Validator validator) {
        super(trainingService, validator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(
            @RequestParam(value = "trainingsessionId", required = true) Long trainingsessionId,
            ModelMap modelMap) {
        TrainingSession session = getTrainingService().obtainTrainingSessionById(trainingsessionId);
        modelMap.addAttribute(session);
        return TRAINING_SESSION_FORM;
    }

}
