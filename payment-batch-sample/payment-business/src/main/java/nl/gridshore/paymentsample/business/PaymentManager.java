package nl.gridshore.paymentsample.business;

import nl.gridshore.paymentsample.domain.Payment;
import nl.gridshore.paymentsample.business.exceptions.PaymentException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 15-aug-2007
 * Time: 23:09:24
 * To change this template use File | Settings | File Templates.
 */
public interface PaymentManager {
    List<Payment> listNonPaidpayments();
    void storePayment(Payment payment) throws PaymentException;
    public Payment loadPaymentById(Integer id);
}
