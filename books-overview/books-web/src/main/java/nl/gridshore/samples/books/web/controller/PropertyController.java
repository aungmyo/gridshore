package nl.gridshore.samples.books.web.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 *
 * @author jettro coenradie
 *         Date: Jan 23, 2009
 */
public abstract class PropertyController implements Controller {
    private static Logger logger = LoggerFactory.getLogger(PropertyController.class);

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("Handle the request for {}",request.getRequestURI());

        Map<String, String> exposedParams = createExposedParamsMap(request);
        return new ModelAndView("propertiesView", "exposedParams", exposedParams);
    }

    abstract protected Map<String, String> createExposedParamsMap(HttpServletRequest request);
}
