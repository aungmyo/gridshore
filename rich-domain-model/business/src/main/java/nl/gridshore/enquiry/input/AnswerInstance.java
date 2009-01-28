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
package nl.gridshore.enquiry.input;

import nl.gridshore.enquiry.def.EnquiryDef;
import nl.gridshore.enquiry.def.QuestionDef;
import nl.gridshore.rdm.persistence.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public abstract class AnswerInstance extends BaseEntity {

    @ManyToOne
    private EnquiryInstance enquiryInstance;

    public EnquiryInstance getEnquiryInstance() {
        return enquiryInstance;
    }

    protected void setEnquiryInstance(final EnquiryInstance enquiryInstance) {
        this.enquiryInstance = enquiryInstance;
    }

    /**
     * Get the question definition for this answer. May never return null;
     *
     * @return the question definition for this answer
     */
    public abstract QuestionDef getQuestionDef();

    public abstract String getText();

    public EnquiryDef getEnquiryDef() {
        return getQuestionDef().getEnquiry();
    }
}
