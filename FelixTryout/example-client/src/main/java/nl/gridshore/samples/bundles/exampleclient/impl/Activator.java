package nl.gridshore.samples.bundles.exampleclient.impl;

import org.osgi.framework.*;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import nl.gridshore.samples.bundles.trainingservice.api.TrainingService;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 9, 2008
 * Time: 10:35:42 PM
 * Activator class for the client
 */
public class Activator implements BundleActivator, ServiceListener {
    private Logger logger = LoggerFactory.getLogger(Activator.class);
    private Server server;
    private ServletHolder servletHolder;
    private BundleContext bundleContext;
    private static final int JETTY_PORT = 8091;
    private static final String JETTY_CONTEXT_PATH = "/";
    private static final String MAIN_SERVLET_PATH_SPEC = "/*";
    private static final String TRAININGS_SERVLET_PATH_SPEC = "/trainings";

    /**
     * Initializes the bundle by adding the service listener, starting the jetty servlet container and add the servlet
     * to the web container.
     * @param bundleContext BundleContext in which this bundle is running
     * @throws Exception thrown when one of the methods throws an exception
     */
    public void start(BundleContext bundleContext) throws Exception {
        logger.debug("Activator for webcontext is started");
        this.bundleContext = bundleContext;

        startJettyServer();


        synchronized (this) {
            bundleContext.addServiceListener(this,"(&(objectClass=" + TrainingService.class.getName() + "))");
        }

        initializeServlet(bundleContext);
    }

    /**
     * Is called when the container stopsm, we use it to stop the server
     * @param bundleContext BundleContext in which this bundle is running
     * @throws Exception thrown when one of the actions throws an exception
     */
    public void stop(BundleContext bundleContext) throws Exception {
        // is done by the framework
        server.stop();
    }

    /**
     * Method called when an event we listen to takes place. We expect only to receive events with a relation to the
     * TrainingService class.
     * @param event ServiceEvent passed with details about the event that took place
     */
    public void serviceChanged(ServiceEvent event) {
        if (event.getType() == ServiceEvent.REGISTERED) {
            logger.info("Training service is registered");
            servletHolder.setServlet(new TrainingsServlet((TrainingService)bundleContext.getService(event.getServiceReference())));
        } else if (event.getType() == ServiceEvent.UNREGISTERING) {
            logger.info("Training service is unregistered");
            servletHolder.setServlet(new NonAvailableServlet());
        } else if (event.getType() == ServiceEvent.MODIFIED) {
            logger.info("Training service is modified");
            servletHolder.setServlet(new TrainingsServlet((TrainingService)bundleContext.getService(event.getServiceReference())));
        }
    }

    private void initializeServlet(BundleContext bundleContext) throws InvalidSyntaxException {
        ServiceReference[] references = bundleContext.getServiceReferences(TrainingService.class.getName(),null);

        if (references != null) {
            TrainingService trainingService = (TrainingService) bundleContext.getService(references[0]);
            servletHolder.setServlet(new TrainingsServlet(trainingService));
        }
    }

    private void startJettyServer() throws Exception {
        server = new Server(JETTY_PORT);
        Context root = new Context(server, JETTY_CONTEXT_PATH, Context.SESSIONS);
        root.addServlet(new ServletHolder(new HelloServlet()), MAIN_SERVLET_PATH_SPEC);
        servletHolder = new ServletHolder();
        servletHolder.setServlet(new NonAvailableServlet());
        root.addServlet(servletHolder, TRAININGS_SERVLET_PATH_SPEC);
        server.start();
    }
}
