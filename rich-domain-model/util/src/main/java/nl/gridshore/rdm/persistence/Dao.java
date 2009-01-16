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

/**
 * Base interface for Data Access Object implementations.
 *
 * @param <T> The type of entity this DAO manages the persistence for
 */
public interface Dao<T extends BaseEntity> {

    /**
     * Persist the given non-persisted entity in the repository.
     *
     * @param entity the entity to persist
     */
    public void create(T entity);

    /**
     * Update the given entity in the repository
     *
     * @param entity the entity to update
     */
    public void update(T entity);

    /**
     * Delete the given entity from the repository
     *
     * @param entity the entity to delete
     */
    public void delete(T entity);

    /**
     * Delete the entity of the type managed by this dao with the given id.
     *
     * @param id The identifier of the entity to delete
     */
    public void delete(long id);

    /**
     * Find the entity of the type managed by this dao with the given id
     *
     * @param id The identifier of the entity to find
     * @return the entity or null if none was found
     */
    T findById(long id);

    /**
     * Find the entity of the type managed by this dao with the given id
     *
     * @param id The identifier of the entity to find
     * @return the entity with the given identifier
     * @throws EntityNotFoundException if the requested entity was not found in the repository
     */
    T load(long id);
}
