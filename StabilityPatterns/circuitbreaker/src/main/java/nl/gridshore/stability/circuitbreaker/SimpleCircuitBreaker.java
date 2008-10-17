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
package nl.gridshore.stability.circuitbreaker;

import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class SimpleCircuitBreaker implements CircuitBreaker, CircuitBreakerStatistics {
    protected AtomicLong failedCalls = new AtomicLong();

    protected volatile Status status = Status.CLOSED;
    protected AtomicReference<Exception> lastException = new AtomicReference<Exception>();
    protected AtomicLong callsAfterFailure = new AtomicLong();

    public void registerCall(String methodName, Object target, Object... args) throws Exception {
        if (status == Status.OPEN) {
            long callCount = callsAfterFailure.incrementAndGet();
            if (callCount != 10) {
                throw lastException.get();
            } else {
                callsAfterFailure.set(0);
            }
        }
    }

    public void registerFailedCall(String methodName, Object target, Exception exception, Object... args) {
        long failed = failedCalls.incrementAndGet();
        if (failed >= 5) {
            status = Status.OPEN;
            lastException.compareAndSet(null, exception);
        }
    }

    public void registerSuccessfulCall(String methodName, Object target, Object retVal, Object... args) {
        if (status == Status.OPEN) {
            status = Status.CLOSED;
            failedCalls.set(0);
            callsAfterFailure.set(0);
            lastException.set(null);
        }
    }

    public Status getStatus() {
        return status;
    }

    public long getFailedCalls() {
        return failedCalls.get();
    }
}

