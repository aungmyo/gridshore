package nl.gridshore.paymentsample.integration;

import nl.gridshore.paymentsample.domain.Payment;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 15-aug-2007
 * Time: 23:34:57
 * Hibernate implementation for the PaymentDao interface
 */
public class PaymentDaoHibernate extends HibernateDaoSupport implements PaymentDao {
    public void save(Payment payment) {
        getHibernateTemplate().saveOrUpdate(payment);
    }

    public List<Payment> findByExample(Payment payment) {
        return getHibernateTemplate().findByExample(payment);
    }

    public List<Payment> findAll() {
        return getHibernateTemplate().loadAll(Payment.class);
    }

    public Payment loadById(Integer id) {
        return (Payment) getHibernateTemplate().load(Payment.class,id);
    }
}
