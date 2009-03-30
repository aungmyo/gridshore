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
import org.hibernate.annotations.Cascade;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class EnquiryDef extends BaseEntity {

    @Column
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enquiry")
//    @OrderBy("index")
    @Cascade({org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    private List<QuestionDef> questions = new ArrayList<QuestionDef>();

    protected EnquiryDef() {
    }

    public EnquiryDef(final String title, final List<QuestionDef> questions) {
        this.title = title;
        int i = 0;
        for (QuestionDef question : questions) {
            Assert.isNull(question.getEnquiry());
            question.setEnquiry(this);
            question.setIndex(i++);
            this.questions.add(question);
        }
    }

    public String getTitle() {
        return title;
    }

    public List<QuestionDef> getQuestions() {
        return Collections.unmodifiableList(questions);
    }
}
