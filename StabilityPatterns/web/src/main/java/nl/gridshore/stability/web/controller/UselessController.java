package nl.gridshore.stability.web.controller;

import nl.gridshore.stability.circuitbreaker.testutils.OperationFailedException;
import nl.gridshore.stability.circuitbreaker.testutils.TestHarness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UselessController {

    private TestHarness testHarness;

    @RequestMapping("/doMonitoredCall")
    public String doMonitoredCall() {
        try {
            testHarness.doMonitoredFailingOperation();
        }
        catch (OperationFailedException ex) {
            return "failed";
        }
        return "success";
    }

    @RequestMapping("/doUnmonitoredCall")
    public String doUnmonitoredCall() {
        try {
            testHarness.doUnmonitoredFailingOperation();
        }
        catch (OperationFailedException ex) {
            return "failed";
        }
        return "success";
    }

    @Autowired
    public void setTestHarness(TestHarness testHarness) {
        this.testHarness = testHarness;
    }
}
