package nl.gridshore.samples.training.web.controllers.training;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.validation.Validator;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import nl.gridshore.samples.training.business.TrainingService;
import nl.gridshore.samples.training.domain.Training;
import nl.gridshore.samples.training.domain.TrainingSession;
import nl.gridshore.samples.training.domain.SessionStatus;
import nl.gridshore.samples.training.web.controllers.training.helpers.CustomSessionStatusEditor;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 27, 2008
 * Time: 5:33:20 PM
 * Add a session to the training
 */
@Controller
@RequestMapping("/addtrainingsession.view")
@SessionAttributes("trainingSession")
public class AddTrainingSessionController extends UpdateableTrainingSessionController {

    @Autowired
    public AddTrainingSessionController(TrainingService trainingService, Validator validator) {
        super(trainingService, validator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(
            @RequestParam(value = "trainingId", required = true) Long trainingId,
            ModelMap modelMap) {
        Training training = getTrainingService().obtainTrainingById(trainingId);
        TrainingSession session = new TrainingSession();
        training.addTrainingSession(session);
        modelMap.addAttribute(session);
        return TRAINING_SESSION_FORM;
    }

}
