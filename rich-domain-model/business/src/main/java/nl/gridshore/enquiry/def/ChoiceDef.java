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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class ChoiceDef extends BaseEntity {

    @ManyToOne(optional = false)
    private MultipleChoiceQuestionDef questionDef;

    @OneToMany(mappedBy = "parentChoiceDef", cascade = CascadeType.ALL)
    @OrderBy("index")
    @Cascade({org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    private List<QuestionDef> subQuestionDefs = new ArrayList<QuestionDef>();

    @Column(name = "ordering")
    private int index;

    @Column
    private String text;

    protected ChoiceDef() {
    }

    public ChoiceDef(final String text) {
        this.text = text;
    }

    public ChoiceDef(final String text, List<QuestionDef> subQuestions) {
        this(text);
        int i = 0;
        for (QuestionDef question : subQuestions) {
            Assert.isNull(question.getEnquiry());
            question.setIndex(i++);
            question.setParentChoiceDef(this);
            subQuestionDefs.add(question);
        }
    }

    public EnquiryDef getEnquiry() {
        if (questionDef == null) {
            return null;
        }
        return questionDef.getEnquiry();
    }

    public List<QuestionDef> getSubQuestions() {
        return Collections.unmodifiableList(subQuestionDefs);
    }

    public MultipleChoiceQuestionDef getQuestionDef() {
        return questionDef;
    }

    protected void setQuestionDef(final MultipleChoiceQuestionDef questionDef) {
        this.questionDef = questionDef;
    }

    protected void setIndex(final int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getText() {
        return text;
    }

}
