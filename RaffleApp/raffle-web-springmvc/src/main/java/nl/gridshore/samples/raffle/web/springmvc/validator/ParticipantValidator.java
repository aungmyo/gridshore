package nl.gridshore.samples.raffle.web.springmvc.validator;

import nl.gridshore.samples.raffle.domain.Participant;
import static nl.gridshore.samples.raffle.web.springmvc.validator.ValidatorConstants.REQUIRED;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 22, 2007
 * Time: 10:45:52 PM
 * Validator class for the particpant
 */
public class ParticipantValidator {
    public void validate(Participant particpant, Errors errors) {
        validateName(particpant.getName(), errors);
    }

    public void validateName(String name, Errors errors) {
        if (!StringUtils.hasLength(name)) {
            errors.rejectValue("name", REQUIRED, REQUIRED);
        }
    }

}
