package nl.gridshore.samples.raffle.dao.jpa;

import nl.gridshore.samples.raffle.dao.BaseDao;
import nl.gridshore.samples.raffle.domain.BaseDomain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 3-nov-2007
 * Time: 0:02:27
 * Implementation for the the baso dao interfacced used as a parent to all the other dao implementations
 */
public abstract class BaseDaoJpa<T extends BaseDomain> implements BaseDao<T> {
    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> prototype;
    private String entityName;

    public BaseDaoJpa(Class<T> prototype, String entityName) {
        this.prototype = prototype;
        this.entityName = entityName;
    }

    public abstract List<T> loadByFilter(T entityFilter);

    public abstract T loadByExample(T entity);

    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T loadById(Long entityId) {
        return entityManager.find(prototype, entityId);
    }

    public List<T> loadAll() {
        Query query = getEntityManager().createQuery("select obj from " + entityName + " obj order by obj.id");
        //noinspection unchecked
        return query.getResultList();
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
