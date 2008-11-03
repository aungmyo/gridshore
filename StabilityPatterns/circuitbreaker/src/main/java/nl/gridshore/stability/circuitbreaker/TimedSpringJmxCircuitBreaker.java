package nl.gridshore.stability.circuitbreaker;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Spring JMX annotated extension of the {@link nl.gridshore.stability.circuitbreaker.TimedCircuitBreaker}
 */
@ManagedResource
public class TimedSpringJmxCircuitBreaker extends TimedCircuitBreaker {

    /**
     * {@inheritDoc}
     */
    @ManagedAttribute
    public String getStatusAsText() {
        return getStatus().name();
    }

    /**
     * {@inheritDoc}
     */
    @ManagedAttribute
    public long getFailedCalls() {
        return super.getFailedCalls();
    }

    /**
     * {@inheritDoc}
     */
    @ManagedAttribute
    public long getBlockedCalls() {
        return super.getBlockedCalls();
    }

    /**
     * {@inheritDoc}
     */
    @ManagedOperation
    public void reset() {
        super.reset();
    }

    /**
     * {@inheritDoc}
     */
    @ManagedAttribute
    public long getRetryThreshold() {
        return super.getRetryThreshold();
    }

    /**
     * {@inheritDoc}
     */
    @ManagedOperation
    public void setRetryThreshold(final long retryThreshold) {
        super.setRetryThreshold(retryThreshold);
    }
}

