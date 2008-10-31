package nl.gridshore.stability.circuitbreaker.testutils;

public class OperationFailedException extends RuntimeException {

    public OperationFailedException(final String s) {
        super(s);
    }
}
