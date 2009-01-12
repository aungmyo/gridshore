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

import nl.gridshore.rdm.persistence.BaseEntity;
import nl.gridshore.rdm.persistence.Repository;

import java.util.HashMap;
import java.util.Map;

public abstract class DomainContext {

    private static enum State {
        OPEN, CLOSED
    }

    private final DomainContextFactory<? extends DomainContext> domainContextFactory;
    private State state = State.OPEN;
    private final Map<BaseEntity, Repository> autoSaveEntities = new HashMap<BaseEntity, Repository>();

    protected DomainContext(final DomainContextFactory<? extends DomainContext> domainContextFactory) {
        this.domainContextFactory = domainContextFactory;
    }

    /**
     * Checks whether the current context is open.
     *
     * @return <code>true</code> if the context is open, otherwise <code>false</code>
     */
    public boolean isOpen() {
        return state == State.OPEN;
    }

    /**
     * Close this context.
     */
    public void close() {
        prepareClose();
        state = State.CLOSED;
        domainContextFactory.removeContext(this);
    }

    /**
     * Register the given <code>entity</code> for autosave using the given <code>repository</code>. The entities are
     * saved just before the context is closed. If the repository uses a Transaction, the context must be closed
     * before the transaction is committed.
     * <p>When an <code>entity</code> is submitted more than once, the last provided <code>repository</code> is used for saving.
     *
     * @param entity     The entity to register for autosave
     * @param repository The repository to use for saving the instance.
     */
    protected void registerForAutoSave(BaseEntity entity, Repository repository) {
        autoSaveEntities.put(entity, repository);
    }

    /**
     * Prepare this context for closing. This is the method where all entities created
     * within this context should be persisted, and resources used by this context must be released.
     */
    @SuppressWarnings({"unchecked"})
    protected void prepareClose() {
        for (Map.Entry<BaseEntity, Repository> entry : autoSaveEntities.entrySet()) {
            if (entry.getKey().getId() == null) {
                entry.getValue().create(entry.getKey());
            } else {
                entry.getValue().update(entry.getKey());
            }

        }
    }
}
