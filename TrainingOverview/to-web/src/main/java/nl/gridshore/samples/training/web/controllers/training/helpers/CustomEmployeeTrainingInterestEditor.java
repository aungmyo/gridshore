package nl.gridshore.samples.training.web.controllers.training.helpers;

import nl.gridshore.samples.training.domain.EmployeeTrainingInterest;

import java.beans.PropertyEditorSupport;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 3, 2008
 * Time: 9:56:38 AM
 * Custom property editor for EmployeeTrainingInterest enumeration
 */
public class CustomEmployeeTrainingInterestEditor extends PropertyEditorSupport {
    public void setAsText(String s) throws IllegalArgumentException {
        if ("".equals(s)) {
            setValue(null);
        } else {
            setValue(EmployeeTrainingInterest.valueOf(s.toUpperCase()));
        }
    }

}
