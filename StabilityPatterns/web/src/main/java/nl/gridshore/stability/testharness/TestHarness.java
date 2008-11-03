package nl.gridshore.stability.testharness;

import nl.gridshore.stability.circuitbreaker.Monitored;

public class TestHarness {

    private volatile int waitTime = 2000;
    private volatile boolean fail = true;

    @Monitored("someWeirdSystem")
    public void doMonitoredFailingOperation() {
        if (fail) {
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                throw new RuntimeException("Did not have enough time to finish operation", e);
            }
            throw new OperationFailedException("That was the failed call");
        }
    }

    public void doUnmonitoredFailingOperation() {
        if (fail) {
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                throw new RuntimeException("Did not have enough time to finish operation", e);
            }
            throw new OperationFailedException("That was the other call");
        }
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(final int waitTime) {
        this.waitTime = waitTime;
    }

    public boolean isFail() {
        return fail;
    }

    public void setFail(final boolean fail) {
        this.fail = fail;
    }
}
