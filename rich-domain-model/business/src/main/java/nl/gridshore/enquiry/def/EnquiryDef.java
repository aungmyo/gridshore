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

import nl.gridshore.enquiry.EnquiryContext;
import nl.gridshore.rdm.persistence.BaseSpringInjectedEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class EnquiryDef extends BaseSpringInjectedEntity {

    @Column
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enquiry")
    @OrderBy("index")
    private List<QuestionDef> questions = new ArrayList<QuestionDef>();

    @Transient
    private EnquiryContext enquiryContext;

    public void appendQuestion(final QuestionDef questionDef) {
        insertQuestion(questionDef, questions.size());
    }

    /**
     * Insert a question at the given position. If position is larger than the number of questions, the question is inserted
     * in the end.
     *
     * @param questionDef The question definition to add
     * @param position    The position to add the question, 0 indicates the first question.
     * @throws IndexOutOfBoundsException if the position has a negative value.
     */
    public void insertQuestion(final QuestionDef questionDef, final int position) {
        questions.remove(questionDef);

        int insertPosition = Math.min(position, questions.size());
        questionDef.setEnquiry(this);
        questions.add(insertPosition, questionDef);
        int t = 1;
        for (QuestionDef question : questions) {
            question.setIndex(t++);
        }
    }

    public QuestionDef removeQuestionIfPresent(final QuestionDef questionDef) {
        if (questions.remove(questionDef)) {
            enquiryContext.registerForDeletion(questionDef);
            return questionDef;
        }
        return null;
    }

    public QuestionDef removeQuestion(final int position) {
        return removeQuestionIfPresent(questions.get(position));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public List<QuestionDef> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    @Autowired
    public void setEnquiryContext(EnquiryContext context) {
        this.enquiryContext = context;
    }
}
