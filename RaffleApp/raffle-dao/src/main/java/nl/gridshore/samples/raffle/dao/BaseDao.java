package nl.gridshore.samples.raffle.dao;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 2-nov-2007
 * Time: 23:51:30
 * Base data access interface used as parent to all the others.
 */
public interface BaseDao<T> {
    T save(T entity);
    T loadByExample(T entity);
    T loadById(Long entityId);
    List<T> loadAll();
    List<T> loadByFilter(T entityFilter);
}
