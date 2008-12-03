package nl.gridshore.samples.hippo.web;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 1, 2008
 * Time: 9:14:18 AM
 * Exception thrown when a problem is detecte while initializing the endpoint
 */
public class EndpointConfigurationException extends RuntimeException {
    public EndpointConfigurationException(String s) {
        super(s);
    }

    public EndpointConfigurationException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
