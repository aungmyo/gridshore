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

/**
 * Abstract base class for all answers in an enquiry instance.
 */
@Entity
public abstract class AnswerInstance extends BaseEntity {

    @ManyToOne
    private EnquiryInstance enquiryInstance;

    /**
     * Get the enquiry instance this answer is linked to. May return null if this answer is not assigned to an enquiry
     * instance yet.
     *
     * @return the enquiry instance this answer is linked to
     */
    public EnquiryInstance getEnquiryInstance() {
        return enquiryInstance;
    }

    /**
     * Setter to be called at the time the answer is connected to an enquiry instance.
     *
     * @param enquiryInstance The enquiry instance this answer is assigned to
     */
    protected void setEnquiryInstance(final EnquiryInstance enquiryInstance) {
        this.enquiryInstance = enquiryInstance;
    }

    /**
     * Get the question definition for this answer. May never return null.
     *
     * @return the question definition for this answer
     */
    public abstract QuestionDef getQuestionDef();

    /**
     * Get the textual representation of this answer. For free text answers, this is the text provided by the user. For
     * multiple-choice answers, it is the comma separated list of answer codes provided by the user.
     *
     * @return The textual representation of the provided answer
     */
    public abstract String getAsText();

    /**
     * Get the enquiry definition of the enquiry this answer is linked to.
     *
     * @return the enquiry definition of the enquiry this answer is linked to
     */
    public EnquiryDef getEnquiryDef() {
        return getQuestionDef().getEnquiry();
    }
}
