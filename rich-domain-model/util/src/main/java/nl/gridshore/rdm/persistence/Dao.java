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
     * @return The identifier of the persisted entity
     */
    public long insert(T entity);

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
