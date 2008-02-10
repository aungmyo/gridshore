package nl.gridshore.samples.bundles.servicelistener.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;

import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 9, 2008
 * Time: 9:17:35 PM
 * Simple activator listening to service events
 */
public class Activator implements BundleActivator, ServiceListener {

    public Activator() {
        System.out.println("Logger is created");
    }

    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Activator started");
        bundleContext.addServiceListener(this);
    }

    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Activator stopped");
        bundleContext.removeServiceListener(this);
    }

    public void serviceChanged(ServiceEvent event) {
        String[] objectClass = (String[])
                event.getServiceReference().getProperty("objectClass");

        if (event.getType() == ServiceEvent.REGISTERED) {
            System.out.println(
                    "Ex1: Service of type " + objectClass[0] + " registered.");
        } else if (event.getType() == ServiceEvent.UNREGISTERING) {
            System.out.println(
                    "Ex1: Service of type " + objectClass[0] + " unregistered.");
        } else if (event.getType() == ServiceEvent.MODIFIED) {
            System.out.println(
                    "Ex1: Service of type " + objectClass[0] + " modified.");
        }
    }
}
