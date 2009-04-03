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
import org.hibernate.annotations.Cascade;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents a question with a number of predefined answers from which one or more may be selected.
 *
 * @see nl.gridshore.enquiry.input.SelectionAnswerInstance SelectionAnswerInstance
 */
@Entity
@DiscriminatorValue("MULTIPLE")
public class MultipleChoiceQuestionDef extends QuestionDef {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionDef")
    @OrderBy("index")
    @Cascade({org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
    protected List<ChoiceDef> choiceDefs = new ArrayList<ChoiceDef>();

    /**
     * Solely fro user by Hibernate/JPA
     */
    protected MultipleChoiceQuestionDef() {
    }

    /**
     * Constructor for a MultipleChoiceQuestionDef
     *
     * @param text       The text (or resource identifier)
     * @param choiceDefs The possible choices for this question
     */
    public MultipleChoiceQuestionDef(final String text, final ChoiceDef... choiceDefs) {
        this(text, Arrays.asList(choiceDefs));
    }

    /**
     * Constructor for a MultipleChoiceQuestionDef
     *
     * @param text       The text (or resource identifier)
     * @param choiceDefs The possible choices for this question
     */
    public MultipleChoiceQuestionDef(final String text, final List<ChoiceDef> choiceDefs) {
        super(text);
        int i = 0;
        for (ChoiceDef choice : choiceDefs) {
            Assert.isNull(choice.getEnquiry(), "One of the provided choiceDefs is already added to another enquiry");
            choice.setIndex(i++);
            choice.setQuestionDef(this);
            this.choiceDefs.add(choice);
        }
    }

    /**
     * Construct an answer to this type of question with the given <code>choiceDefs</code>. Each <code>ChoiceDef</code>
     * represents an option that has been selected.
     *
     * @param choiceDefs The options that have been selected
     * @return an answer instance for this question with the provided choices
     */
    public SelectionAnswerInstance newAnswer(ChoiceDef... choiceDefs) {
        Assert.isTrue(this.choiceDefs.containsAll(Arrays.asList(choiceDefs)),
                      "One or more of the given choices do not belong to this question definition");
        return new SelectionAnswerInstance(this, choiceDefs);
    }

    /**
     * Get the list of choice for this question. The choices are returned in the same order they were provided in in the
     * constructor.
     *
     * @return the list of choice for this question
     */
    public List<ChoiceDef> getChoiceDefs() {
        return Collections.unmodifiableList(choiceDefs);
    }

    @Override
    QuestionDef getSubQuestionByPath(final int[] path) {
        int[] subPath = Arrays.copyOfRange(path, 1, path.length);
        return choiceDefs.get(path[0]).getSubQuestionByPath(subPath);
    }

}
