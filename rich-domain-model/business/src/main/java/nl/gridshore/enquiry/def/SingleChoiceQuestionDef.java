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
import java.util.List;

/**
 * Represents a question where only one of multiple options may be selected.
 *
 * @see nl.gridshore.enquiry.input.SelectionAnswerInstance SelectionAnswerInstance
 */
@Entity
@DiscriminatorValue("SINGLE")
public class SingleChoiceQuestionDef extends MultipleChoiceQuestionDef {

    /**
     * Solely for use by Hibernate/JPA
     */
    protected SingleChoiceQuestionDef() {
    }

    /**
     * Constructor for a SingleChoiceQuestionDef
     *
     * @param text       The text (or resource identifier)
     * @param choiceDefs The possible choices for this question
     */
    public SingleChoiceQuestionDef(final String text, final ChoiceDef... choiceDefs) {
        super(text, choiceDefs);
    }

    /**
     * Constructor for a SingleChoiceQuestionDef
     *
     * @param text       The text (or resource identifier)
     * @param choiceDefs The possible choices for this question
     */
    public SingleChoiceQuestionDef(final String text, final List<ChoiceDef> choiceDefs) {
        super(text, choiceDefs);
    }
}
