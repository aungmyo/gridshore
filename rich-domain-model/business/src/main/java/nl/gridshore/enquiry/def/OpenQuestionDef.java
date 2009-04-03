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
package nl.gridshore.enquiry.def;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Represents an open question. I.e. a question where the user can enter an amount of free text to answer it.
 */
@Entity
@DiscriminatorValue("OPEN")
public class OpenQuestionDef extends QuestionDef {

    /**
     * Constructor solely for use by Hibernate/JPA
     */
    protected OpenQuestionDef() {
    }

    /**
     * Construct a new Open Question Definition instance using the given <code>questionText</code>.
     *
     * @param questionText The text for this question
     */
    public OpenQuestionDef(final String questionText) {
        super(questionText);
    }


}
