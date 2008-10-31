package nl.gridshore.stability.circuitbreaker;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@ManagedResource
public class SimpleCircuitBreaker implements CircuitBreaker, CircuitBreakerStatistics {
    protected AtomicLong failedCalls = new AtomicLong();
    protected AtomicLong blockedCalls = new AtomicLong();
    protected volatile Status status = Status.CLOSED;
    protected AtomicReference<Exception> lastException = new AtomicReference<Exception>();
    protected AtomicLong coolDown = new AtomicLong();
    private static final long THRESHOLD = 10000;

    public void registerCall(String methodName, Object target, Object... args) throws Exception {
        if (status == Status.OPEN) {
            long currentCoolDown = coolDown.get();
            if (currentCoolDown > System.currentTimeMillis() || !coolDown.compareAndSet(currentCoolDown, currentCoolDown + THRESHOLD)) {
                blockedCalls.incrementAndGet();
                throw lastException.get();
            }
        }
    }

    public void registerFailedCall(String methodName, Object target, Exception exception, Object... args) {
        long failed = failedCalls.incrementAndGet();
        if (failed >= 5) {
            status = Status.OPEN;
            lastException.compareAndSet(null, exception);
            coolDown.set(System.currentTimeMillis() + THRESHOLD);
        }
    }

    public void registerSuccessfulCall(String methodName, Object target, Object retVal, Object... args) {
        if (status == Status.OPEN) {
            reset();
        }
    }

    public Status getStatus() {
        return status;
    }

    @ManagedAttribute
    public String getStatusAsText() {
        return status.name();
    }

    @ManagedAttribute
    public long getFailedCalls() {
        return failedCalls.get();
    }

    @ManagedAttribute
    public long getBlockedCalls() {
        return blockedCalls.get();
    }

    @ManagedOperation
    public void reset() {
        status = Status.CLOSED;
        failedCalls.set(0);
        blockedCalls.set(0);
        lastException.set(null);
    }
}

