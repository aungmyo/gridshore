package nl.gridshore.samples.training.web.controllers.training;

import nl.gridshore.samples.training.business.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 24, 2008
 * Time: 1:49:20 PM
 * Controller class for training retrieval pages
 */
@Controller
public class TrainingController {
    private TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @RequestMapping("/trainings.view")
    public ModelMap trainingsHandler() {
        return new ModelMap("trainingsList", trainingService.obtainAllTrainings());
    }

    @RequestMapping("/trainingsessions.view")
    public ModelMap trainingSessionsHander(@RequestParam("trainingId")long traininId) {
        return new ModelMap(trainingService.obtainTrainingById(traininId));
    }
}
