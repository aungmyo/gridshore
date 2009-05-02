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

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Abstract base class for all answers in an enquiry instance.
 */
@Entity
public abstract class AnswerInstance extends BaseEntity {

    @ManyToOne
    private EnquiryInstance enquiryInstance;

    /**
     * Get the enquiry instance this answer is linked to. May return null if this answer is not assigned to an enquiry
     * instance yet.
     *
     * @return the enquiry instance this answer is linked to
     */
    public EnquiryInstance getEnquiryInstance() {
        return enquiryInstance;
    }

    /**
     * Get the question definition for this answer. May never return null.
     *
     * @return the question definition for this answer
     */
    public abstract QuestionDef getQuestionDef();

    /**
     * Get the textual representation of this answer. For free text answers, this is the text provided by the user. For
     * multiple-choice answers, it is the comma separated list of answer codes provided by the user.
     *
     * @return The textual representation of the provided answer
     */
    public abstract String getAsText();

    /**
     * Get the enquiry definition of the enquiry this answer is linked to.
     *
     * @return the enquiry definition of the enquiry this answer is linked to
     */
    public EnquiryDef getEnquiryDef() {
        return getQuestionDef().getEnquiry();
    }

    /**
     * Setter to be called at the time the answer is connected to an enquiry instance.
     *
     * @param enquiryInstance The enquiry instance this answer is assigned to
     */
    void setEnquiryInstance(final EnquiryInstance enquiryInstance) {
        this.enquiryInstance = enquiryInstance;
    }

}
