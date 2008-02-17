package nl.gridshore.samples.bundles.jettyhttpservice.impl;

import org.osgi.service.http.HttpService;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.NamespaceException;
import org.osgi.framework.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mortbay.jetty.servlet.ServletHolder;
import org.mortbay.jetty.servlet.Context;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.util.Map;
import java.util.HashMap;
import java.util.Dictionary;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 17, 2008
 * Time: 8:53:02 PM
 * Implementation of the HttpService interface for jetty
 */
public class JettyHttpService implements HttpService {
    private Logger logger = LoggerFactory.getLogger(JettyHttpService.class);
    private Map<String,ServletHolder> registeredPaths = new HashMap<String,ServletHolder>();

    private Bundle bundle;
    private Context contextRoot;

    public JettyHttpService(Context contextRoot, Bundle bundle) {
        this.bundle = bundle;
        this.contextRoot = contextRoot;
        ServletHolder mainHolder = new ServletHolder(new DummyServlet());
        registeredPaths.put("/*",mainHolder);
        this.contextRoot.addServlet(mainHolder, "/*");

    }

    public void registerServlet(String name, Servlet servlet, Dictionary dictionary, HttpContext httpContext) throws ServletException, NamespaceException {
        logger.info("Register a servlet with name {}", name);

        // check the servlet path and find the holder for that path (if exists)
        if (registeredPaths.containsKey(name)) {
            logger.info("name already exists");
            ServletHolder holder = registeredPaths.get(name);
            holder.setServlet(servlet);
        } else {
            logger.info("name is new");
            ServletHolder holder = new ServletHolder(servlet);
            registeredPaths.put(name,holder);
            contextRoot.addServlet(holder,name);
        }
    }

    public void registerResources(String s, String s1, HttpContext httpContext) throws NamespaceException {
        logger.info("Register a resource");
    }

    public void unregister(String s) {
        logger.info("UnRegister a ??");
    }

    public HttpContext createDefaultHttpContext() {
        logger.info("Create default http context");
        return new JettyHttpContext(bundle);
    }
}
