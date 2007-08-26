package nl.gridshore.paymentsample.business;

import junit.framework.TestCase;

static import org.easymock
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import nl.gridshore.paymentsample.integration.PaymentDao;

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
        this.mockPaymentDao =
        this.paymentManager = new PaymentManagerImpl();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAndDoNothing() {
        assertNotNull(new Integer(1));
    }
}
