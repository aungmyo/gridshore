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

import nl.gridshore.rdm.persistence.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EnquiryDef extends BaseEntity {

    @Column
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enquiry")
    @OrderBy("index")
    private List<QuestionDef> questions = new ArrayList<QuestionDef>();

    public void appendQuestion(final QuestionDef questionDef) {
        this.questions.add(questionDef);
        questionDef.setEnquiry(this);
        questionDef.setIndex(questions.size());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }
}
