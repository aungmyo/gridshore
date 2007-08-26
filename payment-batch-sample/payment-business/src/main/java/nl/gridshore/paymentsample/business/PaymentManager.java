package nl.gridshore.paymentsample.business;

import nl.gridshore.paymentsample.domain.Payment;
import nl.gridshore.paymentsample.business.exceptions.PaymentException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 15-aug-2007
 * Time: 23:09:24
 * The PaymentManager is used to manage all payment related actions. You can use the manager to retrieve payments,
 * update payments, etc.
 */
public interface PaymentManager {
    /**
     * Returns a list will all Payments that are in the status open in the repository
     * @return List of payments that have the status open
     */
    List<Payment> listNonPaidpayments();

    /**
     * Stores the provided payment in the repository. If the Payment does not exist, the status is set to open
     * @param payment The payment to store
     * @throws PaymentException Specific exception that is thrown if something goes wrong            
     */
    void storePayment(Payment payment) throws PaymentException;

    /**
     * Load the payment with the provided id. If a Payment cannot be found, a PaymentException is thrown
     * @param id Integer representing the id of the payment to load
     * @return The payment found by the provided id
     * @throws PaymentException Thrown if the Payment belonging to the id cannot be found or something else goed wrong
     */
    Payment loadPaymentById(Integer id) throws PaymentException;
}
