package nl.gridshore.samples.training.web.controllers.training;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import nl.gridshore.samples.training.business.TrainingService;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 24, 2008
 * Time: 2:14:50 PM
 * Controller class for updating a training
 */
@Controller
@RequestMapping("/edittraining.view")
@SessionAttributes("training")
public class EditTrainingController extends UpdateableTrainingController {

    @Autowired
    public EditTrainingController(TrainingService trainingService, Validator validator) {
        super(trainingService,validator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@RequestParam("trainingId") long trainingId, ModelMap modelMap) {
        modelMap.addAttribute(trainingService.obtainTrainingById(trainingId));
        return TRAINING_FORM;
    }
}
