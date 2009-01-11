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
package nl.gridshore.enquiry.persitance;

import nl.gridshore.enquiry.context.EnquiryContext;
import nl.gridshore.enquiry.context.EnquiryContextFactory;
import nl.gridshore.enquiry.def.EnquiryDef;
import nl.gridshore.enquiry.def.SingleChoiceQuestionDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.jpa.AbstractJpaTests;

public class IntegrationTest extends AbstractJpaTests {

    private EnquiryContextFactory enquiryContextFactory;

    @Override
    protected void onTearDownInTransaction() throws Exception {
        assertNull("Expected all contexts to be closed", enquiryContextFactory.getCurrentContext());
    }

    public void testSaveEnquiryDef() {
        EnquiryContext enquiryContext = enquiryContextFactory.createContext();
        EnquiryDef def;
        final SingleChoiceQuestionDef questionDef;
        try {
            def = enquiryContext.createEnquiryDefForUpdate();
            def.setTitle("Hi there");
            questionDef = new SingleChoiceQuestionDef();
            def.appendQuestion(questionDef);
        }
        finally {
            enquiryContext.close();
        }
        sharedEntityManager.flush();
        assertNotNull(questionDef.getId());
        
        int count = jdbcTemplate.queryForInt("SELECT count(*) FROM EnquiryDef WHERE title = ?", new String[] {"Hi there"});
        assertEquals(1, count);
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath*:/META-INF/spring/*.xml"};
    }

    @Autowired
    public void setEnquiryContextFactory(final EnquiryContextFactory enquiryContextFactory) {
        this.enquiryContextFactory = enquiryContextFactory;
    }
}
