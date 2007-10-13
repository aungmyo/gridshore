package nl.gridshore.paymentsample.integration;

import nl.gridshore.paymentsample.domain.Payment;
import nl.gridshore.paymentsample.domain.PaymentStatus;
import nl.gridshore.paymentsample.domain.PaymentType;
import nl.gridshore.paymentsample.domain.PurchaseOrder;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import java.util.List;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 15-aug-2007
 * Time: 23:57:39
 * Test Class for the PaymentDao
 */
public class PaymentDaoTest extends AbstractTransactionalDataSourceSpringContextTests {
    private PaymentDao paymentDao;
    private PurchaseOrderDao purchaseOrderDao;

    protected String[] getConfigLocations() {
        return new String[]{"spring-integration.xml"};
    }

    public void setPaymentDao(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public void setPurchaseOrderDao(PurchaseOrderDao purchaseOrderDao) {
        this.purchaseOrderDao = purchaseOrderDao;
    }

    public void testLoadPayments() {
        Payment payment = new Payment();
        payment.setStatus(PaymentStatus.OPEN);
        List<Payment> payments = this.paymentDao.findByExample(payment);
        assertNotNull(payments);
        assertEquals("Payment does not have status open", PaymentStatus.OPEN, payments.get(0).getStatus());
    }

    public void  testStoreNewPayment() {
        int beforeNumpayments = this.paymentDao.loadAll().size();
        Payment newPayment = new Payment();
        newPayment.setAmount(56D);
        newPayment.setStatus(PaymentStatus.OPEN);
        newPayment.setType(PaymentType.IDEAL);
        paymentDao.save(newPayment);
        assertEquals("Number of payments retrieved is not correct, so save went wrong",
                beforeNumpayments+1,this.paymentDao.loadAll().size());
    }

//    public void testStoreNewPurchaseOrder() {
//        int beforeNumPurchaseOrders = this.purchaseOrderDao.loadAll().size();
//        PurchaseOrder purchaseOrder = new PurchaseOrder();
//        purchaseOrder.setAmountOfProducts(1);
//        purchaseOrder.setPriceOfProduct(new BigDecimal(50.0));
//        purchaseOrder.setProductName("My test product");
//        Payment newPayment = new Payment();
//        newPayment.setAmount(50.0);
//        newPayment.setStatus(PaymentStatus.OPEN);
//        newPayment.setType(PaymentType.IDEAL);
//        purchaseOrder.setPayment(newPayment);
//        purchaseOrderDao.save(purchaseOrder);
//        assertEquals("Number of purchase orders is not correct, so save went wrong", beforeNumPurchaseOrders+1,
//                this.purchaseOrderDao.loadAll().size());
//    }
}
