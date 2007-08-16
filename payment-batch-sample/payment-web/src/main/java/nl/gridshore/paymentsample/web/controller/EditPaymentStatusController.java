package nl.gridshore.paymentsample.web.controller;

import nl.gridshore.paymentsample.business.PaymentManager;
import nl.gridshore.paymentsample.domain.Payment;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 16-aug-2007
 * Time: 16:10:18
 * To change this template use File | Settings | File Templates.
 */
public class EditPaymentStatusController extends SimpleFormController {
    private PaymentManager paymentManager;

    public EditPaymentStatusController(PaymentManager paymentManager) {
        this.paymentManager = paymentManager;
    }

    protected Object formBackingObject(HttpServletRequest httpServletRequest) throws Exception {
        Integer id = ServletRequestUtils.getRequiredIntParameter(httpServletRequest,"id");
        
        return paymentManager.loadPaymentById(id);
    }

    protected void doSubmitAction(Object command) throws Exception {
        Payment payment = (Payment)command;
        paymentManager.storePayment(payment);
    }
}
