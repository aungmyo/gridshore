package nl.gridshore.samples.bundles.servicelistener.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.gridshore.samples.bundles.trainingservice.api.TrainingService;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 9, 2008
 * Time: 9:17:35 PM
 * Simple activator listening to service events
 */
public class Activator implements BundleActivator, ServiceListener {
    private Logger logger = LoggerFactory.getLogger(Activator.class);
    private BundleContext bundleContext;
    
    public Activator() {
        logger.debug("created");
    }

    public void start(BundleContext bundleContext) throws Exception {
        logger.debug("started.");
        this.bundleContext = bundleContext;
        synchronized (this) {
            this.bundleContext.addServiceListener(this,"(&(objectClass=" + TrainingService.class.getName() + "))");
        }
    }

    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Activator stopped");
        bundleContext.removeServiceListener(this);
    }

    public void serviceChanged(ServiceEvent event) {
        String[] objectClass = (String[]) event.getServiceReference().getProperty("objectClass");

        if (event.getType() == ServiceEvent.REGISTERED) {
            logger.info("Ex1: Service of type {} registered.", objectClass[0]);
        } else if (event.getType() == ServiceEvent.UNREGISTERING) {
            logger.info("Ex1: Service of type {} unregistered.", objectClass[0]);
        } else if (event.getType() == ServiceEvent.MODIFIED) {
            logger.info("Ex1: Service of type {} modified.", objectClass[0]);
        }
    }
}
