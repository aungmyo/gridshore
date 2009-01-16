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

import nl.gridshore.enquiry.EnquiryContext;
import nl.gridshore.enquiry.def.EnquiryDef;
import nl.gridshore.enquiry.def.MultipleChoiceQuestionDef;
import nl.gridshore.enquiry.def.QuestionDef;
import nl.gridshore.enquiry.def.SingleChoiceQuestionDef;
import nl.gridshore.rdm.utils.DomainContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.jpa.AbstractJpaTests;

public class IntegrationTest extends AbstractJpaTests {

    private DomainContextFactory<EnquiryContext> enquiryContextFactory;

    public void testSaveEnquiryDef() {
        EnquiryContext enquiryContext = enquiryContextFactory.createContext();
        assertNotNull("EnquiryContext not properly initialized", enquiryContext);
        EnquiryDef def;
        final SingleChoiceQuestionDef questionDef;
        try {
            def = enquiryContext.createEnquiryDefForUpdate();
            def.setTitle("Hi there");
            questionDef = new SingleChoiceQuestionDef();
            def.appendQuestion(questionDef);
            def.appendQuestion(new MultipleChoiceQuestionDef());
            def.appendQuestion(questionDef);
        }
        finally {
            enquiryContext.close();
        }
        sharedEntityManager.flush();
        assertNotNull(questionDef.getId());

        int count = jdbcTemplate.queryForInt("SELECT count(*) FROM EnquiryDef WHERE title = ?", new String[]{"Hi there"});
        assertEquals(1, count);

        assertEquals(2, def.getQuestions().size());
        assertEquals(1, def.getQuestions().get(0).getIndex());
        assertEquals(2, def.getQuestions().get(1).getIndex());

        sharedEntityManager.flush();
        enquiryContext = enquiryContextFactory.createContext();
        QuestionDef removedDef = def.removeQuestionIfPresent(questionDef);
        assertFalse(def.getQuestions().contains(removedDef));
        enquiryContext.close();
        sharedEntityManager.flush();

        assertEquals(1, jdbcTemplate.queryForInt("SELECT count(*) FROM QuestionDef"));
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[]{"classpath*:/META-INF/spring/*.xml"};
    }

    @Autowired
    public void setEnquiryContextFactory(final DomainContextFactory<EnquiryContext> enquiryContextFactory) {
        this.enquiryContextFactory = enquiryContextFactory;
    }
}
