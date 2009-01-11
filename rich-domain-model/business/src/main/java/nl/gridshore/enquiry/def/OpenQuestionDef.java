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

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

@Entity
@DiscriminatorValue("OPEN")
public class OpenQuestionDef extends QuestionDef {

    @Enumerated(EnumType.STRING)
    private AnswerLength answerLength;
}
