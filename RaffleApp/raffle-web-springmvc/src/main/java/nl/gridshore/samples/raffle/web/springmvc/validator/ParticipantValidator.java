package nl.gridshore.samples.raffle.web.springmvc.validator;

import nl.gridshore.samples.raffle.domain.Participant;
import static nl.gridshore.samples.raffle.web.springmvc.validator.ValidatorConstants.REQUIRED;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 22, 2007
 * Time: 10:45:52 PM
 * Validator class for the particpant
 */
public class ParticipantValidator implements Validator {
    public boolean supports(Class clazz) {
        return Participant.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", REQUIRED, REQUIRED);
    }
}
