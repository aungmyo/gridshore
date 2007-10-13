package nl.gridshore.paymentsample.integration;

import nl.gridshore.paymentsample.domain.BaseDomain;
import org.springframework.orm.ObjectRetrievalFailureException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 29-aug-2007
 * Time: 21:29:47
 * To change this template use File | Settings | File Templates.
 */
public interface BaseDao <T extends BaseDomain> {
    void save(T domainObject);
    void delete(T domainObject);
    void delete(Integer domainObjectId) throws ObjectRetrievalFailureException;
    List<T> loadAll();
    List<T> findByExample(T example);
    T load(Integer id) throws ObjectRetrievalFailureException;
    T getFirstItemFromQueryResultsIfAvailable(String queryString, String[] params, Object[] values);
    T getFirstItemFromQueryResultsIfAvailable(String queryString, String param, Object value);

}
