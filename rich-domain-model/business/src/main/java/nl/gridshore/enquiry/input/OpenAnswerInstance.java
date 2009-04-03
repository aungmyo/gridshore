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

import nl.gridshore.enquiry.def.OpenQuestionDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * An answer to an {@link OpenQuestionDef} open question. This type of answer allows the user to provide an arbitrary
 * amount of free text.
 */
@Entity
public class OpenAnswerInstance extends AnswerInstance {

    @ManyToOne(optional = false)
    private OpenQuestionDef questionDef;

    @Column
    private String text;

    /**
     * Solely for use by Hibernate/JPA
     */
    protected OpenAnswerInstance() {
    }

    /**
     * Constructs an answer to the given {@link OpenQuestionDef open question} using the provided <code>text</code>
     *
     * @param questionDef The question to anwer
     * @param text        The text that makes up the answer
     */
    public OpenAnswerInstance(final OpenQuestionDef questionDef, final String text) {
        this.questionDef = questionDef;
        this.text = text;
    }

    /**
     * Get the question answered by this instance
     *
     * @return the question answered by this instance
     */
    @Override
    public OpenQuestionDef getQuestionDef() {
        return questionDef;
    }

    /**
     * Get the text that makes up this answer
     *
     * @return the text that makes up this answer
     */
    @Override
    public String getAsText() {
        return text;
    }
}
