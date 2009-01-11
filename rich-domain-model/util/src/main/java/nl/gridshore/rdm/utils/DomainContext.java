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
package nl.gridshore.rdm.utils;

public abstract class DomainContext {

    private static enum State {
        OPEN, CLOSED
    }

    private final DomainContextFactory<? extends DomainContext> domainContextFactory;
    private State state = State.OPEN;

    protected DomainContext(final DomainContextFactory<? extends DomainContext> domainContextFactory) {
        this.domainContextFactory = domainContextFactory;
    }

    public boolean isOpen() {
        return state == State.OPEN;
    }

    public void close() {
        state = State.CLOSED;
        prepareClose();
        domainContextFactory.closeContext(this);
    }

    protected abstract void prepareClose();
}
