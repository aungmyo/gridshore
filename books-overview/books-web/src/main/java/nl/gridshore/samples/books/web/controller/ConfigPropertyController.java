package nl.gridshore.samples.books.web.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.HashMap;

/**
 * <p></p>
 *
 * @author jettro coenradie
 *         Date: Jan 23, 2009
 */
public class ConfigPropertyController extends PropertyController {
    protected Map<String, String> createExposedParamsMap(HttpServletRequest request) {
        Map<String, String> exposedParams = new HashMap<String, String>();
        exposedParams.put("host", request.getServerName());
        exposedParams.put("port", String.valueOf(request.getServerPort()));
        String contextRoot = request.getContextPath();
        contextRoot = contextRoot.substring(1);
        exposedParams.put("context-root", contextRoot);
        return exposedParams;
    }
}
