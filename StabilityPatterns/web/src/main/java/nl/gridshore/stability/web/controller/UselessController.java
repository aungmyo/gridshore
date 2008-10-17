/*
 * Copyright (c) 2008 JTeam B.V.
 * www.jteam.nl
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * JTeam B.V. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with JTeam.
 */
package nl.gridshore.stability.web.controller;

import nl.gridshore.stability.circuitbreaker.testutils.TestHarness;
import nl.gridshore.stability.circuitbreaker.testutils.OperationFailedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

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
