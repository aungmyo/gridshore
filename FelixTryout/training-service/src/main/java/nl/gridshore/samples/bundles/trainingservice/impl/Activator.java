package nl.gridshore.samples.bundles.trainingservice.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Properties;

import nl.gridshore.samples.bundles.trainingservice.api.TrainingService;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 9, 2008
 * Time: 10:11:39 PM
 * Activator class for the Training service bundle
 */
public class Activator implements BundleActivator {
    public void start(BundleContext bundleContext) throws Exception {
        bundleContext.registerService(TrainingService.class.getName(),new TrainingServiceImpl(),null);
    }

    public void stop(BundleContext bundleContext) throws Exception {
        // service is automatically unregistered
    }
}
