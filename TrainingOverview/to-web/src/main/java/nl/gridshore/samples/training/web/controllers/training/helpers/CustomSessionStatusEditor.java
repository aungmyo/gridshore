package nl.gridshore.samples.training.web.controllers.training.helpers;

import java.beans.PropertyEditorSupport;

import nl.gridshore.samples.training.domain.SessionStatus;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 29, 2008
 * Time: 8:29:52 PM
 * Propety editor class for SessionStatus enumeration
 */
public class CustomSessionStatusEditor extends PropertyEditorSupport {
    public void setAsText(String s) throws IllegalArgumentException {
        if ("".equals(s)) {
            setValue(null);
        } else {
            setValue(SessionStatus.valueOf(s.toUpperCase()));
        }
    }
}
