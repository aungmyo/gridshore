package nl.gridshore.paymentsample.integration;

import nl.gridshore.paymentsample.domain.Payment;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 15-aug-2007
 * Time: 23:57:39
 * To change this template use File | Settings | File Templates.
 */
public class PaymentDaoTest extends AbstractTransactionalDataSourceSpringContextTests {
    private PaymentDao paymentDao;

    protected String[] getConfigLocations() {
        return new String[]{"spring-integration.xml"};
    }

    public void setPaymentDao(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public void testLoadPayments() {
        Payment payment = new Payment();
        payment.setStatus("Open");
        List<Payment> payments = this.paymentDao.findByExample(payment);
        assertNotNull(payments);
    }

    public void  testStoreNewPayment() {
        int beforeNumpayments = this.paymentDao.findAll().size();
        Payment newPayment = new Payment();
        newPayment.setAmount(56D);
        newPayment.setStatus("open");
        newPayment.setType("ídeal");
        paymentDao.save(newPayment);
        assertEquals("Number of payments retrieved is not correct, so save went wrong",
                beforeNumpayments+1,this.paymentDao.findAll().size());
    }
}
