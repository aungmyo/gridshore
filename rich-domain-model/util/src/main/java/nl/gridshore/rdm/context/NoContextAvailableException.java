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
package nl.gridshore.rdm.context;

/**
 * Exception that indicates that a {@link DomainContext} has been requested while none was
 * currently available.
 */
public class NoContextAvailableException extends RuntimeException {

    public NoContextAvailableException(final String message) {
        super(message);
    }

    public NoContextAvailableException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
