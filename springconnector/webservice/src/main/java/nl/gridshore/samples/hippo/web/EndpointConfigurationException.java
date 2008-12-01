package nl.gridshore.samples.hippo.web;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 1, 2008
 * Time: 9:14:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class EndpointConfigurationException extends RuntimeException {
    public EndpointConfigurationException(String s) {
        super(s);
    }

    public EndpointConfigurationException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
