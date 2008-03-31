package nl.gridshore.samples.training.web.controllers.training;

import nl.gridshore.samples.training.business.TrainingService;
import nl.gridshore.samples.training.domain.TrainingPlanning;
import nl.gridshore.samples.training.domain.TrainingSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;

import java.util.List;

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
    public ModelMap trainingSessionsHander(@RequestParam("trainingId")long trainingId) {
        return new ModelMap(trainingService.obtainTrainingById(trainingId));
    }

    @RequestMapping("/showregistrantstrainingsession.view")
    public ModelMap registrantsTrainingSessionHandler(@RequestParam("trainingsessionId")long trainingsessionId) {
        ModelMap model = new ModelMap();

        List<TrainingPlanning> plannings = trainingService.obtainPlannedAttendenceTrainingSession(trainingsessionId);
        model.addAttribute("trainingPlanningList",plannings);
        TrainingSession session;
        if (plannings.size() > 0) {
            session = plannings.get(0).getSession();
        } else {
            session = trainingService.obtainTrainingSessionById(trainingsessionId);
        }
        model.addAttribute(session);

        return model;
    }
}
