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

public interface CircuitBreaker {
    void registerCall(String methodName, Object target, Object... args) throws Exception;

    void registerFailedCall(String methodName, Object target, Exception exception, Object... args);

    void registerSuccessfulCall(String methodName, Object target, Object retVal, Object... args);
}
