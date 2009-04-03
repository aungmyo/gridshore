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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class SimpleJpaDao<T extends BaseEntity> implements Dao<T> {
    @PersistenceContext
    protected EntityManager entityManager;

    protected final Class<T> entityType;

    public SimpleJpaDao(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public long insert(final T entity) {
        entityManager.persist(entity);
        return entity.getId();
    }

    @Override
    public void update(final T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(final T entity) {
        entityManager.remove(entity);
    }

    @Override
    public void delete(final long id) {
        entityManager.remove(entityManager.getReference(entityType, id));
    }

    @Override
    public T findById(final long id) {
        return entityManager.find(entityType, id);
    }

    @Override
    public T load(final long id) {
        T entity = entityManager.find(entityType, id);
        if (entity == null) {
            throw new EntityNotFoundException(String.format("Entity [%s] with id [%s] not found", entityType, id));
        }
        return entity;
    }
}
