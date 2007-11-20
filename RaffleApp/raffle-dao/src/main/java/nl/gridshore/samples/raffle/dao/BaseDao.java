package nl.gridshore.samples.raffle.dao;

import nl.gridshore.samples.raffle.domain.BaseDomain;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 2-nov-2007
 * Time: 23:51:30
 * Base data access interface used as parent to all the others.
 */
public interface BaseDao<T extends BaseDomain> {

    /**
     * Store an existing entity
     *
     * @param entity Object to store
     * @return Reference to the stored object.
     */
    T save(T entity);

    /**
     * Returns an object belonging to the provided example object. If not exactly one object is found an error is thrown
     *
     * @param entity Exampe object to load from the persistent storage
     * @return The found object
     */
    T loadByExample(T entity);

    /**
     * Load the object belonging to the specified type
     *
     * @param entityId Long representing the type of the object to load
     * @return Object found belonging to the specified type
     */
    T loadById(Long entityId);

    /**
     * Returns  list of all objects
     *
     * @return List of all available objects
     */
    List<T> loadAll();

    /**
     * Return a list with all objects that are valid by the filter
     *
     * @param entityFilter Object of same type that is returned with parameters to filter on
     * @return List of objects of type same as provided filter object
     */
    List<T> loadByFilter(T entityFilter);
}
