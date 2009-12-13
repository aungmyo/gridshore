package nl.gridshore.newsfeed.web.news;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jettro Coenradie
 */
public class CommentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return CommentVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"commenter","field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"content","field.required");
    }

}
