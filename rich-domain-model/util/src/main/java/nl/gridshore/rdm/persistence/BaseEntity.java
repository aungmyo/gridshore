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
package nl.gridshore.rdm.persistence;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Base class for entities that have a primary key column named <code>id</code> of type <code>Long</code> and an
 * optimistic locking column of name <code>lock_version</code> {@link #getLockVersion()} of type <code>long</code>.
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    @Column(name = "lock_version")
    private long lockVersion;

    /**
     * Get the identifier of this entity, or null if this entity has not been persisted yet.
     *
     * @return the identifier of this entity, or null if this entity has not been persisted yet.
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the optimistic locking version of this entity
     *
     * @return the optimistic locking version of this entity
     */
    public long getLockVersion() {
        return lockVersion;
    }

    /**
     * Sets the identifier of this entity
     *
     * @param id the new id for this entity
     */
    protected void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the version used for optimistic locking of this entity
     *
     * @param lockVersion the new lockVersion for this entity
     */
    protected void setLockVersion(long lockVersion) {
        this.lockVersion = lockVersion;
    }
}
