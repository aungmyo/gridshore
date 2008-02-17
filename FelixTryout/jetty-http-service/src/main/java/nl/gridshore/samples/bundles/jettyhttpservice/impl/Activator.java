package nl.gridshore.samples.bundles.jettyhttpservice.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.http.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 17, 2008
 * Time: 8:45:42 PM
 * Activator class for a custom implementation related to jetty runtime of the OSGi HttpService.
 */
public class Activator implements BundleActivator {
    private Logger logger = LoggerFactory.getLogger(Activator.class);
    private final static int JETTY_PORT = 9081;
    private final static String JETTY_CONTEXT_ROOT = "/";

    private Server server;

    /**
     * Start method for the bundle. During the starting we start an instance of the Jetty embedded runtime.
     * @param bundleContext BundleContext injected during the start
     * @throws Exception thrown when an error occurs
     */
    public void start(BundleContext bundleContext) throws Exception {
        server = new Server(JETTY_PORT);
        Context rootContext = new Context(server, JETTY_CONTEXT_ROOT, Context.SESSIONS);
        HttpService httpService = new JettyHttpService(rootContext,bundleContext.getBundle());
        server.start();
        bundleContext.registerService(HttpService.class.getName(),httpService,null);
    }

    /**
     * Stop method for the bundle, must stop the jetty instance as well.
     * @param bundleContext BundleContext injected during the stop
     * @throws Exception thrown when an error occurs
     */
    public void stop(BundleContext bundleContext) throws Exception {
        server.stop();
    }
}
