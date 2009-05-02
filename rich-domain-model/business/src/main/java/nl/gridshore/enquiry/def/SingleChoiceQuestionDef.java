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

import nl.gridshore.enquiry.input.SelectionAnswerInstance;
import org.springframework.util.Assert;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Arrays;
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

    /**
     * Construct an answer to this type of question with the given <code>choiceDefs</code>. Each <code>ChoiceDef</code>
     * represents an option that has been selected. Note that this type of question only allows one option to be
     * selected
     *
     * @param choiceDefs The options that have been selected
     * @return an answer instance for this question with the provided choices
     * @throws IllegalArgumentException if more than one choice is given or one of the provided choices is not valid for
     *                                  this question.
     */
    public SelectionAnswerInstance newAnswer(ChoiceDef... choiceDefs) {
        Assert.isTrue(choiceDefs.length == 1, "This type of question allows only one choice");
        Assert.isTrue(this.choiceDefs.containsAll(Arrays.asList(choiceDefs)),
                      "One or more of the given choices do not belong to this question definition");
        return new SelectionAnswerInstance(this, choiceDefs);
    }

    // ======================== Helper methods ==============================

    SingleChoiceQuestionDef() {
        // needed for Hibernate
    }

}
