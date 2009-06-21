/*
 * Copyright (c) 2009. Gridshore
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.gridshore.rdm.persistence;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;

/**
 * Base class for entities that have a primary key column named <code>id</code> of type <code>Long</code> and an
 * optimistic locking column of name <code>lock_version</code> {@link #getLockVersion()} of type <code>long</code>.
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

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
     * Sets the identifier of this entity. Exclusively for testing.
     *
     * @param id the new id for this entity
     */
    void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns true if this instance has the same identity as <code>other</code>. The identity is regarded equal if the
     * <code>id</code> of both objects is equal and not null, and both instances are from the exact same class.
     *
     * @param other The instance to compare
     * @return True if the instances have the same identity, otherwise false.
     */
    public boolean sameIdentityAs(BaseEntity other) {
        return other != null && id != null && getClass().equals(other.getClass()) && id.equals(other.getId());
    }

    /**
     * Sets the version used for optimistic locking of this entity. Exclusively for testing.
     *
     * @param lockVersion the new lockVersion for this entity
     */
    void setLockVersion(long lockVersion) {
        this.lockVersion = lockVersion;
    }

}
