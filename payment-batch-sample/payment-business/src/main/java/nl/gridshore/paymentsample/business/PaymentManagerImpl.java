package nl.gridshore.paymentsample.business;

import nl.gridshore.paymentsample.business.exceptions.PaymentException;
import nl.gridshore.paymentsample.domain.Payment;
import nl.gridshore.paymentsample.domain.PaymentStatus;
import nl.gridshore.paymentsample.integration.PaymentDao;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 15-aug-2007
 * Time: 23:16:47
 * Implementation for the PaymentManager interface
 */
public class PaymentManagerImpl implements PaymentManager {
    private PaymentDao paymentDao;

    /**
     * Constructor used to inject the required PaymentDao, an exception is thrown if the dao is null
     * @param paymentDao Dataaccess object for payments
     * @throws nl.gridshore.paymentsample.business.exceptions.PaymentException when the provided dao is null
     */
    public PaymentManagerImpl(PaymentDao paymentDao) throws PaymentException {
        if (paymentDao == null) {
            throw new PaymentException("The PaymentDao cannot be null, configuration error??");
        }
        this.paymentDao = paymentDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<Payment> listNonPaidpayments() {
        Payment payment = new Payment();
        payment.setStatus(PaymentStatus.OPEN);

        return paymentDao.findByExample(payment);  
    }

    /**
     * {@inheritDoc}
     */
    public void storePayment(Payment payment) throws PaymentException {
        if (payment.getId() == null) {
            payment.setStatus(PaymentStatus.OPEN);
        }
        paymentDao.save(payment);
    }

    /**
     * @{inheritDoc}
     */
    public Payment loadPaymentById(Integer id) {
        return paymentDao.loadById(id);
    }
}
