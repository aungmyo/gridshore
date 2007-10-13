package nl.gridshore.paymentsample.business;

import junit.framework.TestCase;
import nl.gridshore.paymentsample.domain.Payment;
import nl.gridshore.paymentsample.domain.PaymentStatus;
import nl.gridshore.paymentsample.domain.PaymentType;
import nl.gridshore.paymentsample.integration.PaymentDao;
import nl.gridshore.paymentsample.business.impl.PaymentManagerImpl;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

/**
 * PaymentManagerImpl Tester.
 *
 * @author Jettro Coenradie
 * @since <pre>08/16/2007</pre>
 * @version 1.0
 */
public class PaymentManagerImplTest extends TestCase {
    private PaymentManager paymentManager;
    private PaymentDao mockPaymentDao;

    public PaymentManagerImplTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
        this.mockPaymentDao = createMock(PaymentDao.class);
        this.paymentManager = new PaymentManagerImpl(mockPaymentDao);
    }

    public void testListNonPaidpayments() {
        reset(mockPaymentDao);
        Payment searchPayment = new Payment();
        searchPayment.setStatus(PaymentStatus.OPEN);
        List<Payment> returnPayments = new ArrayList<Payment>();
        Payment foundPayment1 = createFoundPayment();
        returnPayments.add(foundPayment1);
        expect(mockPaymentDao.findByExample(searchPayment)).andReturn(returnPayments);
        replay(mockPaymentDao);

        List<Payment> foundPayments = paymentManager.listNonPaidpayments();

        verify(mockPaymentDao);

        assertNotNull(foundPayments);
        assertEquals("number of returned payments is not correct", returnPayments.size(),foundPayments.size());
    }

    public void testStorePayment() {
        reset(mockPaymentDao);
        Payment newPayment = new Payment();
        newPayment.setAmount(23.3);
        newPayment.setType(PaymentType.IDEAL);
        mockPaymentDao.save(newPayment);
        replay(mockPaymentDao);
        paymentManager.storePayment(newPayment);
        verify(mockPaymentDao);
        assertEquals("Status of payment is not set to open", PaymentStatus.OPEN,newPayment.getStatus());
    }

    private Payment createFoundPayment() {
        Payment foundPayment1 = new Payment();
        foundPayment1.setId(1);
        foundPayment1.setAmount(34D);
        foundPayment1.setType(PaymentType.CREDITCARD);
        foundPayment1.setStatus(PaymentStatus.OPEN);
        return foundPayment1;
    }


}
