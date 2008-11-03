package nl.gridshore.stability.circuitbreaker;

import nl.gridshore.stability.testharness.TestHarness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import java.io.IOException;

public class TimedSpringJmxCircuitBreakerTest extends AbstractDependencyInjectionSpringContextTests {

    private TimedSpringJmxCircuitBreaker timedSpringJmxCircuitBreaker;
    private TestHarness testHarness;

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath:/context/circuitbreaker-config.xml", "classpath:/context/testharness-config.xml"};
    }

    public void testCircuitBreakerOpens() throws IOException, InterruptedException {
        testHarness.setFail(false);
        testHarness.setWaitTime(0);

        assertEquals(Status.CLOSED, timedSpringJmxCircuitBreaker.getStatus());

        testHarness.doMonitoredFailingOperation();

        assertEquals(Status.CLOSED, timedSpringJmxCircuitBreaker.getStatus());

        testHarness.setFail(true);
        for (int t = 0; t < 4; t++) {
            try {
                testHarness.doMonitoredFailingOperation();
            }
            catch (RuntimeException ex) {
                // swallow
            }
        }
        assertEquals(Status.CLOSED, timedSpringJmxCircuitBreaker.getStatus());
        try {
            testHarness.doMonitoredFailingOperation();
        }
        catch (RuntimeException ex) {
            // swallow
        }
        assertEquals(Status.OPEN, timedSpringJmxCircuitBreaker.getStatus());
    }

    public void setTestHarness(final TestHarness testHarness) {
        this.testHarness = testHarness;
    }

    @Autowired
    public void setSimpleCircuitBreaker(final @Qualifier("circuitBreaker")TimedSpringJmxCircuitBreaker timedSpringJmxCircuitBreaker) {
        this.timedSpringJmxCircuitBreaker = timedSpringJmxCircuitBreaker;
    }
}
