package nl.gridshore.stability.circuitbreaker;

public interface CircuitBreaker {
    /**
     * Register an intended call with the circuit breaker.
     *
     * @param methodName The name of the intercepted method
     * @param target     The object on which the intercepted method is called
     * @param args       The arguments of the intercepted method call
     * @throws Exception to prevent the actual call being made. Typically, this is the last exception originally returned by the intercepted method call.
     */
    void registerCall(String methodName, Object target, Object... args) throws Exception;

    /**
     * Register a failed call. Based on this registration, the circuit breaker may change its state.
     *
     * @param methodName The name of the intercepted method
     * @param target     The object on which the intercepted method is called
     * @param exception  the exception thrown by the intercepted method call
     * @param args       The arguments of the intercepted method call
     */
    void registerFailedCall(String methodName, Object target, Exception exception, Object... args);

    /**
     * Register a succesful method call. Based on this registration, the circuit breaker may change its state
     *
     * @param methodName The name of the intercepted method
     * @param target     The object on which the intercepted method was called
     * @param retVal     the return calue of the intercepted method call
     * @param args       The arguments of the intercepted method call
     */
    void registerSuccessfulCall(String methodName, Object target, Object retVal, Object... args);
}
