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
    private List<QuestionDef> subQuestionDefs = new ArrayList<QuestionDef>();

    @Column(name = "ordering")
    private int index;

    @Column
    private String text;

    protected ChoiceDef() {
    }

    public ChoiceDef(final MultipleChoiceQuestionDef questionDef, final String text) {
        this.questionDef = questionDef;
        this.text = text;
    }

    public EnquiryDef getEnquiry() {
        return questionDef.getEnquiry();
    }

    public void appendSubQuestion(QuestionDef questionDef) {
        insertSubQuestion(questionDef, subQuestionDefs.size());
    }

    public void insertSubQuestion(QuestionDef questionDef, final int position) {
        subQuestionDefs.remove(questionDef);

        int insertPosition = Math.min(position, subQuestionDefs.size());
        questionDef.setParentChoiceDef(this);
        subQuestionDefs.add(insertPosition, questionDef);
        int t = 1;
        for (QuestionDef question : subQuestionDefs) {
            question.setIndex(t);
        }
    }

    public List<QuestionDef> getSubQuestions() {
        return Collections.unmodifiableList(subQuestionDefs);
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
