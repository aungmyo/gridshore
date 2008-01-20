package nl.gridshore.samples.training.business.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 10:25:07 PM
 * RUntime exception that all business exceptions should extend
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String s) {
        super(s);
    }

    public BusinessException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
