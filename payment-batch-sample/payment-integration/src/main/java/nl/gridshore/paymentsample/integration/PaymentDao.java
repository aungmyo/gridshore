package nl.gridshore.paymentsample.integration;

import nl.gridshore.paymentsample.domain.Payment;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 15-aug-2007
 * Time: 23:35:33
 * To change this template use File | Settings | File Templates.
 */
public interface PaymentDao {
    void save(Payment payment);
    List<Payment> findByExample(Payment payment);
    List<Payment> findAll();
    Payment loadById(Integer id);
}
