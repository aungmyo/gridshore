package nl.gridshore.paymentsample.business;

import nl.gridshore.paymentsample.business.exceptions.PaymentException;
import nl.gridshore.paymentsample.domain.Payment;
import nl.gridshore.paymentsample.integration.PaymentDao;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 15-aug-2007
 * Time: 23:16:47
 * To change this template use File | Settings | File Templates.
 */
public class PaymentManagerImpl implements PaymentManager {
    private PaymentDao paymentDao;

    public PaymentManagerImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public List<Payment> listNonPaidpayments() {
        Payment payment = new Payment();
        payment.setStatus("open");

        return paymentDao.findByExample(payment);  
    }

    public void storePayment(Payment payment) throws PaymentException {
        paymentDao.save(payment);
    }

    public Payment loadPaymentById(Integer id) {
        return paymentDao.loadById(id);
    }
}
