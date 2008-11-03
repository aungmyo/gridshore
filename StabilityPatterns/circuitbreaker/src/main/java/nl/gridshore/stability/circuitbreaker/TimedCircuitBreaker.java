package nl.gridshore.stability.circuitbreaker;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 2, 2008
 * Time: 10:50:53 PM
 * Implementation of the Circuit breaker with a time based retry mechanism
 */
public class TimedCircuitBreaker implements CircuitBreaker, CircuitBreakerStatistics {

    /**
     * The number of failed calls since the circuit breaker closed.
     */
    private AtomicLong failedCalls = new AtomicLong();

    /**
     * The number of calls that must fail before the circuit breaker is opened.
     */
    private volatile long failedCallThreshold = 5L;

    /**
     * The number of calls blocked by the circuit breaker. Nice for statistics
     */
    private AtomicLong blockedCalls = new AtomicLong();

    /**
     * The current status of the circuit breaker
     */
    private volatile Status status = Status.CLOSED;

    /**
     * The last exception registered with the circuit breaker. Should be <code>null</code> if the status is <code>CLOSED</cod>.
     */
    private AtomicReference<Exception> lastException = new AtomicReference<Exception>();

    /**
     * The amount of time in milliseconds before the circuit breaker will retry a connection
     */
    private volatile long retryThreshold = 10000L;

    /**
     * The time at which the circuit breaker may retry the connection
     */
    private AtomicLong coolDown = new AtomicLong();


    /**
     * {@inheritDoc}
     */
    public void registerCall(String methodName, Object target, Object... args) throws Exception {
        if (status == Status.OPEN) {
            long currentCoolDown = coolDown.get();
            if (currentCoolDown > System.currentTimeMillis() || !coolDown.compareAndSet(currentCoolDown, currentCoolDown + retryThreshold)) {
                blockedCalls.incrementAndGet();
                throw lastException.get();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void registerFailedCall(String methodName, Object target, Exception exception, Object... args) {
        long failed = failedCalls.incrementAndGet();
        if (failed >= failedCallThreshold) {
            status = Status.OPEN;
            lastException.compareAndSet(null, exception);
            coolDown.set(System.currentTimeMillis() + retryThreshold);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void registerSuccessfulCall(String methodName, Object target, Object retVal, Object... args) {
        if (status == Status.OPEN) {
            reset();
        }
    }

    /**
     * {@inheritDoc}
     */
    public Status getStatus() {
        return status;
    }


    public long getFailedCalls() {
        return failedCalls.get();
    }


    public long getBlockedCalls() {
        return blockedCalls.get();
    }

    /* Helper methods */
    protected void reset() {
        status = Status.CLOSED;
        failedCalls.set(0);
        blockedCalls.set(0);
        lastException.set(null);
    }

    protected long getRetryThreshold() {
        return retryThreshold;
    }

    protected void setRetryThreshold(final long retryThreshold) {
        this.retryThreshold = retryThreshold;
    }

}
