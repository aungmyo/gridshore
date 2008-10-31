package nl.gridshore.stability.circuitbreaker;

import nl.gridshore.stability.circuitbreaker.testutils.TestHarness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import java.io.IOException;

public class SimpleCircuitBreakerTest extends AbstractDependencyInjectionSpringContextTests {

    private SimpleCircuitBreaker simpleCircuitBreaker;
    private TestHarness testHarness;

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"context/circuitbreaker-config.xml", "/context/testharness-config.xml"};
    }

    public void testCircuitBreakerOpens() throws IOException, InterruptedException {
        testHarness.setWaitTime(0);
        assertEquals(Status.CLOSED, simpleCircuitBreaker.getStatus());

        testHarness.doMonitoredFailingOperation();

        assertEquals(Status.CLOSED, simpleCircuitBreaker.getStatus());

        testHarness.setFail(true);
        for (int t = 0; t < 4; t++) {
            try {
                testHarness.doMonitoredFailingOperation();
            }
            catch (RuntimeException ex) {
                // swallow
            }
        }
        assertEquals(Status.CLOSED, simpleCircuitBreaker.getStatus());
        try {
            testHarness.doMonitoredFailingOperation();
        }
        catch (RuntimeException ex) {
            // swallow
        }
        assertEquals(Status.OPEN, simpleCircuitBreaker.getStatus());
    }

    public void setTestHarness(final TestHarness testHarness) {
        this.testHarness = testHarness;
    }

    @Autowired
    public void setSimpleCircuitBreaker(final @Qualifier("circuitBreaker")SimpleCircuitBreaker simpleCircuitBreaker) {
        this.simpleCircuitBreaker = simpleCircuitBreaker;
    }
}
