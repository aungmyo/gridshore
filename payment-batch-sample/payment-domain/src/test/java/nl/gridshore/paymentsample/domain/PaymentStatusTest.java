package nl.gridshore.paymentsample.domain;

import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 16-aug-2007
 * Time: 15:55:20
 * Testcase for a payment status
 */
public class PaymentStatusTest extends TestCase {
    public void testEquals() {
        assertEquals(PaymentStatus.OPEN,PaymentStatus.valueOf("OPEN"));
    }
}
