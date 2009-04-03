/*
 * Copyright (c) 2009. Gridshore
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.gridshore.enquiry.def;

import nl.gridshore.rdm.persistence.BaseEntity;
import org.springframework.util.Assert;

import javax.persistence.*;

/**
 * Abstract base class for all questions
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING, length = 50)
@Table(name = "QuestionDef")
public abstract class QuestionDef extends BaseEntity {

    @Column
    protected String questionText;

    @ManyToOne(optional = true)
    protected EnquiryDef enquiry;

    @Column(name = "ordering")
    protected int index;

    @ManyToOne(optional = true)
    @JoinColumn
    protected ChoiceDef parentChoiceDef;

    /**
     * Solely for use by Hibernate/JPA
     */
    protected QuestionDef() {
    }

    /**
     * Constructor to use by subclasses to properly construct a valid question instance
     *
     * @param questionText The text that forms the question
     */
    protected QuestionDef(final String questionText) {
        this.questionText = questionText;
    }

    /**
     * Get the Enquiry Definition that this question is part of
     *
     * @return the Enquiry Definition that this question is part of
     */
    public EnquiryDef getEnquiry() {
        if (parentChoiceDef != null) {
            return parentChoiceDef.getEnquiry();
        }
        return enquiry;
    }

    /**
     * Get the text that forms this questions
     *
     * @return the text that forms this questions
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Get the 0-based index of this question. The index indicates the position relative to other questions in an
     * enquiry.
     *
     * @return the 0-based index of this question
     */
    public int getIndex() {
        return index;
    }

    /**
     * Set the enquiry that this question belongs to. This method is to be called at the moment this question is added
     * as a root question to an enquiry.
     *
     * @param enquiry the enquiry to register with the question
     */
    void setEnquiry(final EnquiryDef enquiry) {
        Assert.isNull(parentChoiceDef, "Cannot add sub questions to an Enquiry Definition directly");
        this.enquiry = enquiry;
    }

    /**
     * Set the choice for which this question is a sub question.
     *
     * @param parentChoiceDef the choice for which this question is a sub question.
     */
    void setParentChoiceDef(final ChoiceDef parentChoiceDef) {
        Assert.isNull(this.enquiry, "Cannot add as sub question when added to an Enquiry Definition directly");
        this.parentChoiceDef = parentChoiceDef;
    }

    /**
     * Set the 0-based index for this question. The index indicates the position of this question relative to other
     * questions in the same context
     *
     * @param index the 0-based index of this question in its context
     */
    void setIndex(final int index) {
        this.index = index;
    }
}
