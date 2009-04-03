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

import nl.gridshore.enquiry.def.OpenQuestionDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * An answer to an {@link OpenQuestionDef} open question. This type of answer allows the user to provide an arbitrary
 * amount of free text.
 */
@Entity
public class OpenAnswerInstance extends AnswerInstance {

    @ManyToOne(optional = false)
    private OpenQuestionDef questionDef;

    @Column
    private String text;

    /**
     * Solely for use by Hibernate/JPA
     */
    protected OpenAnswerInstance() {
    }

    /**
     * Constructs an answer to the given {@link OpenQuestionDef open question} using the provided <code>text</code>
     *
     * @param questionDef The question to anwer
     * @param text        The text that makes up the answer
     */
    public OpenAnswerInstance(final OpenQuestionDef questionDef, final String text) {
        this.questionDef = questionDef;
        this.text = text;
    }

    /**
     * Get the question answered by this instance
     *
     * @return the question answered by this instance
     */
    @Override
    public OpenQuestionDef getQuestionDef() {
        return questionDef;
    }

    /**
     * Get the text that makes up this answer
     *
     * @return the text that makes up this answer
     */
    @Override
    public String getAsText() {
        return text;
    }
}
