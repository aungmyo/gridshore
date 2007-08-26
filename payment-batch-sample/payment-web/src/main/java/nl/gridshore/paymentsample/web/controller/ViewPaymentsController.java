package nl.gridshore.paymentsample.web.controller;

import nl.gridshore.paymentsample.business.PaymentManager;
import nl.gridshore.paymentsample.domain.Payment;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 16-aug-2007
 * Time: 10:45:19
 * Controller class for viewing all payments
 */
public class ViewPaymentsController extends AbstractController {
    private final PaymentManager paymentManager;

    public ViewPaymentsController(PaymentManager paymentManager) {
        this.paymentManager = paymentManager;
    }

    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        List<Payment> payments = paymentManager.listNonPaidpayments();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(payments);
        return modelAndView;
    }
}
