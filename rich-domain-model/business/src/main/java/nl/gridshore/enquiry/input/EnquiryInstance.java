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

package nl.gridshore.enquiry.input;

import nl.gridshore.enquiry.def.EnquiryDef;
import nl.gridshore.enquiry.def.QuestionDef;
import nl.gridshore.rdm.persistence.BaseEntity;
import org.hibernate.annotations.Cascade;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an instance of an enquiry as defined by the provided {@link EnquiryDef Enquiry Definition}, containing the
 * answers of an enquiry.
 */
@Entity
public class EnquiryInstance extends BaseEntity {

    @ManyToOne(optional = false)
    private EnquiryDef enquiryDef;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enquiryInstance")
    @Cascade({org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    private List<AnswerInstance> answerInstances = new ArrayList<AnswerInstance>();

    private transient Map<QuestionDef, AnswerInstance> answerMap = new HashMap<QuestionDef, AnswerInstance>();

    /**
     * Solely for use by Hibernate/JPA
     */
    protected EnquiryInstance() {
    }

    /**
     * Primary constructor for an Enquiry instance.
     *
     * @param enquiryDef The non-null definition that this enquiry is constructed for
     */
    public EnquiryInstance(final EnquiryDef enquiryDef) {
        Assert.notNull(enquiryDef, "The provided EnquiryDef may not be null");
        this.enquiryDef = enquiryDef;
    }

    /**
     * Get all provided answers for this enquiry instance. The answers are not provided in any perticular order.
     *
     * @return the list of provided answers for this enquiry instance.
     */
    public List<AnswerInstance> getAnswers() {
        return Collections.unmodifiableList(answerInstances);
    }

    /**
     * Get the enquiry definition for this enquiry instance
     *
     * @return the enquiry definition for this enquiry instance
     */
    public EnquiryDef getEnquiryDef() {
        return enquiryDef;
    }

    /**
     * Add an answer to this enquiry instance. If the question has already been answered, the existing answer is
     * replaced by the provided one.
     *
     * @param answerInstance The answer to add to this enquiry instance
     * @throws IllegalArgumentException if the answer belongs to another enquiry than the current
     */
    public void addAnswer(final AnswerInstance answerInstance) {
        Assert.notNull(answerInstance, "The provided AnswerInstance may not be null");
        Assert.isTrue(getEnquiryDef().equals(answerInstance.getEnquiryDef()), "The answer belongs to another enquiry than this instance");

        removeAnswerForQuestion(answerInstance.getQuestionDef());
        answerInstances.add(answerInstance);
        answerInstance.setEnquiryInstance(this);
        answerMap.put(answerInstance.getQuestionDef(), answerInstance);
    }

    /**
     * Get the anser that was provided for the given question. Returns null if the question has not been answered yet.
     *
     * @param questionDef The question definition to find the answer for
     * @return The {@link AnswerInstance} for the given question
     */
    public AnswerInstance getAnswerForQuestion(QuestionDef questionDef) {
        return answerMap.get(questionDef);
    }

    /**
     * Remove the answer of the given {@link QuestionDef} from this enquiry instance
     *
     * @param questionDef The question definition to remove the answer for
     */
    protected void removeAnswerForQuestion(final QuestionDef questionDef) {
        AnswerInstance currentAnswer = getAnswerForQuestion(questionDef);
        if (currentAnswer != null) {
            currentAnswer.setEnquiryInstance(null);
            answerInstances.remove(currentAnswer);
            answerMap.remove(currentAnswer.getQuestionDef());
        }
    }

    /**
     * Populate the map of answers for quick lookup of answers for specific questions. This method is to be called after
     * injection of <code>answerInstances</code> has taken place.
     */
    @PostLoad
    protected void populateAnswerMap() {
        answerMap.clear();
        for (AnswerInstance answer : answerInstances) {
            answerMap.put(answer.getQuestionDef(), answer);
        }
    }
}
