package nl.gridshore.newsfeed.web.controllers;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author Jettro Coenradie
 */
public abstract class GaeSpringController {
    /**
     * Spring uses a classloader to find the required editors that are used during the binding process. This
     * feature is not supported by google app engine. Therefore we need to provide the binders explicitly.
     *
     * @param binder WebDataBinder that is used to register the editors
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
        binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
//        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }    
}
