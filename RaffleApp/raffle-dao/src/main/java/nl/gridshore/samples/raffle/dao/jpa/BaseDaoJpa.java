package nl.gridshore.samples.raffle.dao.jpa;

import nl.gridshore.samples.raffle.dao.BaseDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 3-nov-2007
 * Time: 0:02:27
 * Implementation for the the baso dao interfacced used as a parent to all the other dao implementations
 */
public class BaseDaoJpa<T> implements BaseDao<T> {
    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> prototype;

    public BaseDaoJpa(Class<T> prototype) {
        this.prototype = prototype;
    }

    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T loadByExample(T entity) {
        return null;
    }

    public T loadById(Long entityId) {
        return entityManager.find(prototype,entityId);
    }

    public List<T> loadAll() {
        return null;
    }

    public List<T> loadByFilter(T entityFilter) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    protected final T newPrototype(Class<T> cl) throws IllegalArgumentException {
        try{
            return cl.newInstance();
        }catch(Exception e){
            throw new IllegalArgumentException(e);
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
