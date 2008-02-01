package nl.gridshore.samples.training.integration.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 9:13:15 PM
 * Exception thrown when something is wrong with the input data
 */
public class IntegrationInputDataException extends IntegrationException {
    public IntegrationInputDataException(String s) {
        super(s);
    }
}
