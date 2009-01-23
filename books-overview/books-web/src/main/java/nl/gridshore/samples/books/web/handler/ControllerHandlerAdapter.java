package nl.gridshore.samples.books.web.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p></p>
 *
 * @author jettro coenradie
 *         Date: Jan 23, 2009
 */
public class ControllerHandlerAdapter implements HandlerAdapter, ServletConfigAware {
    private static Logger logger = LoggerFactory.getLogger(ControllerHandlerAdapter.class);

    private ServletConfig servletConfig;

    public boolean supports(Object handler) {
        return handler instanceof Controller;
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("Handle a request using the handler {}", handler.getClass().getName());

        Controller controller = (Controller) handler;
        return controller.handleRequest(request, response);
    }

    public long getLastModified(HttpServletRequest request, Object handler) {
        return -1;
    }

    public void setServletConfig(ServletConfig servletConfig) {
        this.servletConfig = servletConfig;
    }
}
