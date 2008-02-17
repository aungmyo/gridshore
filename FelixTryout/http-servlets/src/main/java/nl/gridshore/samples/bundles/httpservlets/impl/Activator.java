package nl.gridshore.samples.bundles.httpservlets.impl;

import org.osgi.framework.*;
import org.osgi.service.http.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import nl.gridshore.samples.bundles.trainingservice.api.TrainingService;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 17, 2008
 * Time: 10:07:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Activator implements BundleActivator, ServiceListener {
    private Logger logger = LoggerFactory.getLogger(Activator.class);

    public void start(BundleContext bundleContext) throws Exception {

        ServiceReference[] trainingReferences = bundleContext.getServiceReferences(TrainingService.class.getName(), null);
        TrainingService trainingService = null;
        if (trainingReferences != null) {
            trainingService = (TrainingService) bundleContext.getService(trainingReferences[0]);
        }

        ServiceReference[] httpReferences = bundleContext.getServiceReferences(HttpService.class.getName(), null);
        HttpService httpService = null;
        if (httpReferences != null) {
            httpService = (HttpService) bundleContext.getService(httpReferences[0]);
        }

        if ((trainingService != null) && (httpService != null)) {
            logger.info("training service and http service are both registered");
            httpService.registerServlet("/trainings", new TrainingsServlet(trainingService), null, null);
        }

        synchronized (this) {
            bundleContext.addServiceListener(this,
                    "(|(objectClass=" + TrainingService.class.getName() + ")" +
                            "(objectClass=" + HttpService.class.getName() + "))");
        }

    }

    public void stop(BundleContext bundleContext) throws Exception {

    }

    public void serviceChanged(ServiceEvent event) {
        String objectClass = ((String[]) event.getServiceReference().getProperty("objectClass"))[0];

        if (event.getType() == ServiceEvent.REGISTERED) {
            if (objectClass.equals(TrainingService.class.getName())) {
                logger.info("Training Service registered. {}", objectClass);
            } else {
                logger.info("Http Service registered. {}", objectClass);
            }
        } else if (event.getType() == ServiceEvent.UNREGISTERING) {
            if (objectClass.equals(TrainingService.class.getName())) {
                logger.info("Training Service unregistered. {}", objectClass);
            } else {
                logger.info("Http Service unregistered. {}", objectClass);
            }
        } else if (event.getType() == ServiceEvent.MODIFIED) {
            if (objectClass.equals(TrainingService.class.getName())) {
                logger.info("Training Service modified. {}", objectClass);
            } else {
                logger.info("Http Service modified. {}", objectClass);
            }
        }
    }
}
