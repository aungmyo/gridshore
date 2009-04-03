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

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The definition of an Enquiry. An enquiry is regarded as an immutable and ordered collection of questions.
 */
@Entity
public class EnquiryDef extends BaseEntity {

    @Basic
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enquiry")
    @OrderBy("index")
    @Cascade({org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    private List<QuestionDef> questions = new ArrayList<QuestionDef>();

    /**
     * Constructor solely for use by hibernate/JPA
     */
    protected EnquiryDef() {
    }

    /**
     * Constructs a new Enquiry Definition instance with the given <code>title</code> and <code>questions</code>.
     *
     * @param title     The title of this enquiry
     * @param questions The questions that make up this enquiry. Each of those questions must be root level questions.
     */
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

    /**
     * Get the title of this enquiry as provided in the constructor
     *
     * @return the title of this enquiry definition
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the questions in this enquiry. Only returns the root-level questions.
     *
     * @return an immutable list of questions.
     */
    public List<QuestionDef> getQuestions() {
        return Collections.unmodifiableList(questions);
    }
}
