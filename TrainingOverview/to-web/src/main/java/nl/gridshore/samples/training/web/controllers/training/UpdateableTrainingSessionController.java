package nl.gridshore.samples.training.web.controllers.training;

import nl.gridshore.samples.training.business.TrainingService;
import nl.gridshore.samples.training.domain.TrainingSession;
import nl.gridshore.samples.training.web.controllers.ControllerConstants;
import nl.gridshore.samples.training.web.controllers.training.helpers.CustomSessionStatusEditor;
import org.springframework.validation.Validator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.WebDataBinder;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 27, 2008
 * Time: 5:33:04 PM
 * Parent class for all controller that do something while updating training sessons
 */
public class UpdateableTrainingSessionController {
    protected static final String SESSION_OBJECT_NAME = "trainingSession";
    protected static final String REDIRECT_TRAININGS_VIEW = "redirect:trainings.view";
    protected static final String TRAINING_SESSION_FORM = "trainingsessionForm";

    protected TrainingService trainingService;
    protected Validator validator;

    public UpdateableTrainingSessionController(TrainingService trainingService, Validator validator) {
        this.trainingService = trainingService;
        this.validator = validator;
    }

    protected Collection<String> createPossibleValuesSessionStatus() {

        nl.gridshore.samples.training.domain.SessionStatus[] sessionStatuses = nl.gridshore.samples.training.domain.SessionStatus.values();
        Collection<String> statussus = new HashSet<String>();
        for (nl.gridshore.samples.training.domain.SessionStatus sessionStatus : sessionStatuses) {
            statussus.add(sessionStatus.toString());
        }
        return statussus;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(nl.gridshore.samples.training.domain.SessionStatus.class,new CustomSessionStatusEditor());
    }

    @ModelAttribute("sessionStatusses")
    public Collection<String> populateSessionStatusValues() {
        return createPossibleValuesSessionStatus();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processFormSubmit(
            @RequestParam(value = ControllerConstants.REQUEST_PARAM_CANCEL, required = false) String cancel,
            @ModelAttribute(SESSION_OBJECT_NAME)TrainingSession trainingSession,
            BindingResult bindingResult,
            SessionStatus status) {
        String returnValue;
        if (ControllerConstants.REQUEST_PARAM_CANCEL_VALUE.equals(cancel)) {
            returnValue = REDIRECT_TRAININGS_VIEW;
        } else {
            validator.validate(trainingSession,bindingResult);
            if (bindingResult.hasErrors()) {
                returnValue = TRAINING_SESSION_FORM;
            } else {
                trainingService.storeTraining(trainingSession.getTraining());
                status.setComplete();
                returnValue = REDIRECT_TRAININGS_VIEW;
            }
        }
        return returnValue;
    }

    protected TrainingService getTrainingService() {
        return trainingService;
    }
}
