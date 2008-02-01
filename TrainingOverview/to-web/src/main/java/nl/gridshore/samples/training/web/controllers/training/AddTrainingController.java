package nl.gridshore.samples.training.web.controllers.training;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import nl.gridshore.samples.training.business.TrainingService;
import nl.gridshore.samples.training.domain.Training;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 24, 2008
 * Time: 2:15:03 PM
 * Controller class to add a new training
 */
@Controller
@RequestMapping("/addtraining.view")
@SessionAttributes("training")
public class AddTrainingController extends UpdateableTrainingController {

    @Autowired
    public AddTrainingController(TrainingService trainingService, Validator validator) {
        super(trainingService, validator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(ModelMap modelMap) {
        modelMap.addAttribute(new Training());
        return TRAINING_FORM;
    }

}
