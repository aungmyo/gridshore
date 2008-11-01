package nl.gridshore.stability.circuitbreaker;

public interface CircuitBreakerStatistics {
    long getFailedCalls();

    long getBlockedCalls();
}
