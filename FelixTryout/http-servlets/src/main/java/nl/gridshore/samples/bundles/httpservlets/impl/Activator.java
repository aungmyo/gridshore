package nl.gridshore.samples.bundles.httpservlets.impl;

import org.osgi.framework.*;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import nl.gridshore.samples.bundles.trainingservice.api.TrainingService;

import javax.servlet.ServletException;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 17, 2008
 * Time: 10:07:44 PM
 * BundleActivator for the http-servlets bundle. This bundles exposes servlets and other resources with respect to
 * training activities. You can start by going to:
 * http://.../index.html
 *
 * This sevice needs two other services:
 * 1. http.service : generic osgi service
 * 2. training-service : service provided by gridshore
 *
 * There is a machanism in place that servlet is only registered if both services are available. If one of them
 * disappears, the servlet will go down.
 */
public class Activator implements BundleActivator, ServiceListener {
    private Logger logger = LoggerFactory.getLogger(Activator.class);
    private BundleContext bundleContext;

    public void start(BundleContext bundleContext) throws Exception {
        this.bundleContext = bundleContext;

        doRegister();

        synchronized (this) {
            bundleContext.addServiceListener(this,
                    "(|(objectClass=" + TrainingService.class.getName() + ")" +
                    "(objectClass=" + HttpService.class.getName() + "))");
        }
    }

    public void stop(BundleContext bundleContext) throws Exception {
        doUnregister();
    }

    public void serviceChanged(ServiceEvent event) {
        String objectClass = ((String[]) event.getServiceReference().getProperty("objectClass"))[0];
        logger.info("Service change event occurred for : {}", objectClass );
        if (event.getType() == ServiceEvent.REGISTERED) {
            doRegister();
        } else if (event.getType() == ServiceEvent.UNREGISTERING) {
            doUnregister();
        } else if (event.getType() == ServiceEvent.MODIFIED) {
            doUnregister();
            doRegister();
        }
    }

    private void register() throws InvalidSyntaxException, ServletException, NamespaceException {
        ServiceReference[] trainingReferences = bundleContext.getServiceReferences(TrainingService.class.getName(), null);
        TrainingService trainingService = null;
        if (trainingReferences != null) {
            trainingService = (TrainingService) bundleContext.getService(trainingReferences[0]);
        } else {
            logger.info("No training service available");
        }

        ServiceReference[] httpReferences = bundleContext.getServiceReferences(HttpService.class.getName(), null);
        HttpService httpService = null;
        if (httpReferences != null) {
            httpService = (HttpService) bundleContext.getService(httpReferences[0]);
        } else {
            logger.info("No http service available");
        }

        if ((trainingService != null) && (httpService != null)) {
            logger.info("training servlet will be registered.");
            httpService.registerServlet("/trainings", new TrainingsServlet(trainingService), null, null);
            httpService.registerResources("/","/htmls",null);
        } else {
            logger.info("No servlet to register, problem with training service or http service");
        }
    }

    private void unregister() throws InvalidSyntaxException {
        logger.info("Unregister a servlet");
        ServiceReference[] httpReferences = bundleContext.getServiceReferences(HttpService.class.getName(), null);
        if (httpReferences != null) {
            HttpService httpService = (HttpService) bundleContext.getService(httpReferences[0]);
            httpService.unregister("/trainings");
            httpService.unregister("/");
        }
    }

    private void doUnregister() {
        try {
            unregister();
        } catch (InvalidSyntaxException e) {
            logger.error("Could not unregister servlet",e);
        }
    }

    private void doRegister() {
        try {
            register();
        } catch (InvalidSyntaxException e) {
            logger.error("Could not register servlet based on an Invalid Syntax",e);
        } catch (ServletException e) {
            logger.error("Could not register servlet based on an Servlet exception",e);
        } catch (NamespaceException e) {
            logger.error("Could not register servlet based on an Namespace exception",e);
        }
    }
}
