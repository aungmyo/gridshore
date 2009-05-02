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

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Represents an open question. I.e. a question where the user can enter an amount of free text to answer it.
 */
@Entity
@DiscriminatorValue("OPEN")
public class OpenQuestionDef extends QuestionDef {

    /**
     * Construct a new Open Question Definition instance using the given <code>questionText</code>.
     *
     * @param questionText The text for this question
     */
    public OpenQuestionDef(final String questionText) {
        super(questionText);
    }

    // ======================== Helper methods ==============================

    @Override
    QuestionDef getSubQuestionByPath(final int[] path) {
        throw new UnsupportedOperationException("Subquestions are not supported for open questions");
    }

    OpenQuestionDef() {
        // needed by Hibernate
    }

}
