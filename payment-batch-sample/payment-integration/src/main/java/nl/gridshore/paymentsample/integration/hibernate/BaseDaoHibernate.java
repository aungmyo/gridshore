package nl.gridshore.paymentsample.integration.hibernate;

import nl.gridshore.paymentsample.integration.BaseDao;
import nl.gridshore.paymentsample.domain.BaseDomain;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 29-aug-2007
 * Time: 21:36:30
 * To change this template use File | Settings | File Templates.
 */
public class BaseDaoHibernate<T extends BaseDomain> extends HibernateDaoSupport implements BaseDao<T> {
    private T prototype;

    public BaseDaoHibernate(Class<T> p1) {
        prototype = newPrototype(p1);
    }

    public void save(T obj) {
        getHibernateTemplate().saveOrUpdate(obj);
    }

    public void delete(T obj) {
        getHibernateTemplate().delete(obj);
    }

    @SuppressWarnings("unchecked")
    public List<T> loadAll() {
        return (List<T>)getHibernateTemplate().loadAll(prototype.getClass());
    }

    @SuppressWarnings("unchecked")
    public List<T> findByExample(final T obj) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
            throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(prototype.getClass());
                Example productQuery = Example.create(obj);
                productQuery.enableLike();
                productQuery.ignoreCase();
                criteria.add(productQuery);
                return criteria.list();
            }
        });
    }

    @SuppressWarnings("unchecked")
    public T load(Integer id) throws ObjectRetrievalFailureException {
        T returnObject = (T) getHibernateTemplate().load(prototype.getClass(),id);
        if (returnObject == null) {
            throw new ObjectRetrievalFailureException(prototype.getClass(),id);
        }
        return returnObject;
    }

    @SuppressWarnings("unchecked")
    public T getFirstItemFromQueryResultsIfAvailable(String queryString, String[] params, Object[] values) {
        List<T> items = getHibernateTemplate().findByNamedParam(queryString, params, values);
        T item = null;
        if (items.size() > 0) {
            item = items.get(0);
        }
        return item;

    }

    public T getFirstItemFromQueryResultsIfAvailable(String queryString, String param, Object value) {
        return getFirstItemFromQueryResultsIfAvailable(queryString, new String[]{param}, new Object[]{value});
    }


    public void delete(Integer id) {
        delete(load(id));
    }

    protected final T newPrototype(Class<T> cl) throws IllegalArgumentException {
        try{
            return cl.newInstance();
        }catch(Exception e){
            throw new IllegalArgumentException(e);
        }
    }
}
