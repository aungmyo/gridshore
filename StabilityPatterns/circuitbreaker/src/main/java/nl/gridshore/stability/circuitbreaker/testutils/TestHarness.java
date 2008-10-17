/*
 * Copyright (c) 2008 JTeam B.V.
 * www.jteam.nl
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * JTeam B.V. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with JTeam.
 */
package nl.gridshore.stability.circuitbreaker.testutils;

import nl.gridshore.stability.circuitbreaker.Monitored;

public class TestHarness {

    private volatile int waitTime = 5000;
    private volatile boolean fail = false;

    @Monitored("someWeirdSystem")
    public void doMonitoredFailingOperation() {
        if (fail) {
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                throw new RuntimeException("Did not have enough time to finish operation", e);
            }
            throw new OperationFailedException("That was the other call");
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
