package nl.gridshore.paymentsample.integration.hibernate;

import nl.gridshore.paymentsample.domain.Payment;
import nl.gridshore.paymentsample.integration.PaymentDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 15-aug-2007
 * Time: 23:34:57
 * Hibernate implementation for the PaymentDao interface
 */
public class PaymentDaoHibernate extends BaseDaoHibernate<Payment> implements PaymentDao {
    public PaymentDaoHibernate() {
        super(Payment.class);
    }
}
