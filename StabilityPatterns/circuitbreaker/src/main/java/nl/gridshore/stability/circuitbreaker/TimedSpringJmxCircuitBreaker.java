package nl.gridshore.stability.circuitbreaker;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;

import javax.management.AttributeChangeNotification;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Spring JMX annotated extension of the {@link nl.gridshore.stability.circuitbreaker.TimedCircuitBreaker}
 */
@ManagedResource
public class TimedSpringJmxCircuitBreaker extends TimedCircuitBreaker implements NotificationPublisherAware {

    private AtomicLong t = new AtomicLong(0);

    private NotificationPublisher notificationPublisher;

    @Override
    protected void sendStateChangeNotification(Status oldStatus, Status newStatus) {
        notificationPublisher.sendNotification(new AttributeChangeNotification(this, t.getAndIncrement(), System.currentTimeMillis(), "Circuit breaker state changed", "Status", "Status", oldStatus.name(), newStatus.name()));
    }

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

    public void setNotificationPublisher(final NotificationPublisher notificationPublisher) {
        this.notificationPublisher = notificationPublisher;
    }
}

