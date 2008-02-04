package nl.gridshore.samples.training.web.controllers.training;

import nl.gridshore.samples.training.business.TrainingService;
import nl.gridshore.samples.training.domain.TrainingSession;
import nl.gridshore.samples.training.web.controllers.ControllerConstants;
import nl.gridshore.samples.training.web.controllers.training.helpers.CustomSessionStatusEditor;
import nl.gridshore.samples.training.web.helpers.SelectOption;
import org.springframework.validation.Validator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

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

    protected MessageSource messageSource;

    protected TrainingService trainingService;
    protected Validator validator;

    public UpdateableTrainingSessionController(TrainingService trainingService, Validator validator) {
        this.trainingService = trainingService;
        this.validator = validator;
    }

    protected Collection<SelectOption> createPossibleValuesSessionStatus(Locale locale) {

        nl.gridshore.samples.training.domain.SessionStatus[] sessionStatuses =
                nl.gridshore.samples.training.domain.SessionStatus.values();
        Collection<SelectOption> statussus = new HashSet<SelectOption>();
        for (nl.gridshore.samples.training.domain.SessionStatus sessionStatus : sessionStatuses) {
            statussus.add(new SelectOption(
                    sessionStatus.toString(),
                    messageSource.getMessage(sessionStatus.toString(),null,sessionStatus.toString(), locale)));
        }
        return statussus;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(nl.gridshore.samples.training.domain.SessionStatus.class,new CustomSessionStatusEditor());
    }

    @ModelAttribute("sessionStatusses")
    public Collection<SelectOption> populateSessionStatusValues(Locale locale) {
        return createPossibleValuesSessionStatus(locale);
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

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
