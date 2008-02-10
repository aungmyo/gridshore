package nl.gridshore.samples.felix;

import org.apache.felix.framework.Felix;
import org.apache.felix.framework.cache.BundleCache;
import org.apache.felix.framework.util.StringMap;
import org.apache.felix.framework.util.FelixConstants;
import org.apache.felix.main.AutoActivator;
import org.osgi.framework.Constants;
import org.osgi.framework.BundleException;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 8, 2008
 * Time: 9:17:27 PM
 * Simple launcher for felix osgi container
 */
public class SimpleLauncher {
    private static Felix felix = null;
    private HostActivator activator = null;
    
    public SimpleLauncher() {
        System.out.println("\nWelcome to Felix.");
        System.out.println("=================\n");

        //noinspection unchecked
        Map<String,String> configMap = new StringMap(false);
        configMap.put(FelixConstants.EMBEDDED_EXECUTION_PROP, "true");
        configMap.put(Constants.FRAMEWORK_SYSTEMPACKAGES,
                "org.osgi.framework; version=1.3.0," +
                "org.osgi.service.packageadmin; version=1.2.0," +
                "org.osgi.service.startlevel; version=1.0.0," +
                "org.osgi.service.url; version=1.0.0");
        configMap.put(AutoActivator.AUTO_START_PROP + ".1",
                "file:/Users/jettro/sources/temp/FelixMaven/service-listener/target/service-listener-1.0-SNAPSHOT.jar " +
                "file:/Users/jettro/sources/temp/FelixMaven/training-service/target/training-service-1.0-SNAPSHOT.jar " +
                "file:/Users/jettro/sources/temp/FelixMaven/example-client/target/example-client-1.0-SNAPSHOT.jar " +
                "file:bundle/org.apache.felix.shell-1.0.0.jar " +
                "file:bundle/org.apache.felix.shell.tui-1.0.0.jar ");
        configMap.put(BundleCache.CACHE_PROFILE_DIR_PROP, "cache");

        List<BundleActivator> activators = new ArrayList<BundleActivator>();
        activators.add(new AutoActivator(configMap));
        activator = new HostActivator();
        activators.add(activator);
        felix = new Felix(configMap, activators);
        try {
            felix.start();
        } catch (BundleException e) {
            System.err.println("Could not create framework: " + e);
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public List<String> getBundleNames() {
        List<String> bundleNames = new ArrayList<String>();
        for (Bundle bundle : activator.getBundles()) {
            bundleNames.add(bundle.getSymbolicName());
        }
        return bundleNames;
    }

    public void shutDownpplication() {
        try {
            felix.stop();
        } catch (BundleException e) {
            System.err.println("Could not stop framework: " + e);
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
