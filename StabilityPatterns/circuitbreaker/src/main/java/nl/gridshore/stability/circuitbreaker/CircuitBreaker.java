package nl.gridshore.stability.circuitbreaker;

public interface CircuitBreaker {
    void registerCall(String methodName, Object target, Object... args) throws Exception;

    void registerFailedCall(String methodName, Object target, Exception exception, Object... args);

    void registerSuccessfulCall(String methodName, Object target, Object retVal, Object... args);
}
