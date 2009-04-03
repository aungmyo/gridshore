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
import java.util.List;

/**
 * Represents a question where only one of multiple options may be selected.
 *
 * @see nl.gridshore.enquiry.input.SelectionAnswerInstance SelectionAnswerInstance
 */
@Entity
@DiscriminatorValue("SINGLE")
public class SingleChoiceQuestionDef extends MultipleChoiceQuestionDef {

    /**
     * Solely for use by Hibernate/JPA
     */
    protected SingleChoiceQuestionDef() {
    }

    /**
     * Constructor for a SingleChoiceQuestionDef
     *
     * @param text       The text (or resource identifier)
     * @param choiceDefs The possible choices for this question
     */
    public SingleChoiceQuestionDef(final String text, final ChoiceDef... choiceDefs) {
        super(text, choiceDefs);
    }

    /**
     * Constructor for a SingleChoiceQuestionDef
     *
     * @param text       The text (or resource identifier)
     * @param choiceDefs The possible choices for this question
     */
    public SingleChoiceQuestionDef(final String text, final List<ChoiceDef> choiceDefs) {
        super(text, choiceDefs);
    }
}
