package nl.gridshore.samples.raffle.business.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 9, 2008
 * Time: 8:18:28 PM
 * Common exception used when strange input parameters are provided.
 */
public class InputParameterException extends RuntimeException {
    public InputParameterException(String message) {
        super(message);
    }
}
