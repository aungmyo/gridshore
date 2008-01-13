package nl.gridshore.samples.raffle.web.springmvc.validator;

import static nl.gridshore.samples.raffle.web.springmvc.validator.ValidatorConstants.REQUIRED;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 22, 2007
 * Time: 8:51:54 PM
 * Validator class for the Raffle domain object used by the frontend technologies
 */
public class RaffleValidator implements Validator {
    public boolean supports(Class clazz) {
        return RaffleValidator.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "title", REQUIRED, REQUIRED);
        ValidationUtils.rejectIfEmpty(errors, "description", REQUIRED, REQUIRED);
    }
}
