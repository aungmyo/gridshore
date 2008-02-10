package nl.gridshore.samples.bundles.exampleclient.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;
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
    private Server server;

    public void start(BundleContext bundleContext) throws Exception {
        ServiceReference[] references = bundleContext.getServiceReferences(TrainingService.class.getName(),null);

        if (references != null) {
            TrainingService trainingService = (TrainingService) bundleContext.getService(references[0]);
            List<String> trainings = trainingService.obtainTrainings();
            for (String training : trainings) {
                System.out.println("Training name : " + training);
            }
            server = new Server(8091);
            Context root = new Context(server,"/",Context.SESSIONS);
            root.addServlet(new ServletHolder(new HelloServlet(trainingService)), "/*");
            server.start();
        }

    }

    public void stop(BundleContext bundleContext) throws Exception {
        // is done by the framework
        server.stop();
    }
}
