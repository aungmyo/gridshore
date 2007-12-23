package nl.gridshore.samples.raffle.web.springmvc.validator;

import nl.gridshore.samples.raffle.domain.Raffle;
import static nl.gridshore.samples.raffle.web.springmvc.validator.ValidatorConstants.REQUIRED;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 22, 2007
 * Time: 8:51:54 PM
 * Validator class for the Raffle domain object used by the frontend technologies
 */
public class RaffleValidator {
    public void validate(Raffle raffle, Errors errors) {
        validateTitle(raffle.getTitle(), errors);
        validateDescription(raffle.getDescription(), errors);
    }

    public void validateTitle(String title, Errors errors) {
        if (!StringUtils.hasLength(title)) {
            errors.rejectValue("title", REQUIRED, REQUIRED);
        }
    }

    public void validateDescription(String description, Errors errors) {
        if (!StringUtils.hasLength(description)) {
            errors.rejectValue("description", REQUIRED, REQUIRED);
        }
    }
}
