package nl.gridshore.google;

/**
 * Exception class thrown when something fails in the AnalyticsService class
 */
public class AnalyticsServiceException extends RuntimeException{
    public AnalyticsServiceException(String s) {
        super(s);
    }

    public AnalyticsServiceException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
