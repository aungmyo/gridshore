package nl.gridshore.samples.bundles.exampleclient.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import nl.gridshore.samples.bundles.trainingservice.api.TrainingService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 9, 2008
 * Time: 10:35:42 PM
 * Activator class for the client
 */
public class Activator implements BundleActivator {
    public void start(BundleContext bundleContext) throws Exception {
        ServiceReference[] references = bundleContext.getServiceReferences(TrainingService.class.getName(),"(Version=*)");

        if (references != null) {
            TrainingService trainingService = (TrainingService) bundleContext.getService(references[0]);
            List<String> trainings = trainingService.obtainTrainings();
            for (String training : trainings) {
                System.out.println("Training name : " + training);
            }
        }
    }

    public void stop(BundleContext bundleContext) throws Exception {
        // is done by the framework
    }
}
