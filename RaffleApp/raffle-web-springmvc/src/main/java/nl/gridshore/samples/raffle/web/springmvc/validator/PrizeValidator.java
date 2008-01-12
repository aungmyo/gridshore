package nl.gridshore.samples.raffle.web.springmvc.validator;

import nl.gridshore.samples.raffle.domain.Prize;
import static nl.gridshore.samples.raffle.web.springmvc.validator.ValidatorConstants.REQUIRED;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;


/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 23, 2007
 * Time: 8:19:37 AM
 * Validator class for validating Prize objects
 */
public class PrizeValidator {
    public void validate(Prize prize, Errors errors) {
        validateTitle(prize.getTitle(), errors);
        validateDescription(prize.getDescription(), errors);
    }

    private void validateTitle(String title, Errors errors) {
        if (!StringUtils.hasLength(title)) {
            errors.rejectValue("title", REQUIRED, REQUIRED);
        }
    }

    private void validateDescription(String description, Errors errors) {
        if (!StringUtils.hasLength(description)) {
            errors.rejectValue("description", REQUIRED, REQUIRED);
        }
    }
}
