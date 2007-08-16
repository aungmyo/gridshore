package nl.gridshore.paymentsample.domain;

import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 16-aug-2007
 * Time: 15:55:20
 * To change this template use File | Settings | File Templates.
 */
public class PaymentStatusTest extends TestCase {
    public void testEquals() {
        assertEquals(PaymentStatus.OPEN,PaymentStatus.valueOf("OPEN"));
        Payment payment = new Payment();
        payment.setStatus("open");
    }
}
