package nl.gridshore.samples.training.dataaccess.jpa;

import nl.gridshore.samples.training.dataaccess.BaseDao;
import nl.gridshore.samples.training.domain.BaseDomain;
import org.springframework.orm.ObjectRetrievalFailureException;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 5:03:11 PM
 * Standard implementation for the base interface data access objects
 */
public class BaseDaoJpa<T extends BaseDomain> implements BaseDao<T> {
    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> prototype;
    private String entityName;

    public BaseDaoJpa(Class<T> prototype, String entityName) {
        this.prototype = prototype;
        this.entityName = entityName;
    }
    
    public T save(T entity) {
        if (entity.getId() != null) {
            entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
        }
        return entity;
    }

    public T loadById(Long entityId) throws ObjectRetrievalFailureException {
        T entity = entityManager.find(prototype, entityId);
        if (entity == null) {
            throw new ObjectRetrievalFailureException(prototype, entityId);
        }
        return entity;
    }

    public List<T> loadAll() {
        Query query = entityManager.createQuery("select obj from " + entityName + " obj order by obj.id");
        //noinspection unchecked
        return query.getResultList();
    }

    public void delete(final T entity) {
        T loadedEntity = loadById(entity.getId());
        entityManager.remove(loadedEntity);
    }

    protected final T newPrototype(Class<T> cl) throws IllegalArgumentException {
        try {
            return cl.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
