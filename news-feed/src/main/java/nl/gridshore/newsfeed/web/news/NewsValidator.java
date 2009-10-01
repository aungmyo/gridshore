package nl.gridshore.newsfeed.web.news;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Jettro Coenradie
 */
public class NewsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return NewsItemVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"author","field.required");
    }
}
