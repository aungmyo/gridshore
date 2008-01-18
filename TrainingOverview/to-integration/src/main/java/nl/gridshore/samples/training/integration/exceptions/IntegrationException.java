package nl.gridshore.samples.training.integration.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 9:11:38 PM
 * Generic Runtime exception that is the parent for all other integration exceptions.
 */
public class IntegrationException extends RuntimeException {

    public IntegrationException(String s) {
        super(s);
    }

    public IntegrationException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
