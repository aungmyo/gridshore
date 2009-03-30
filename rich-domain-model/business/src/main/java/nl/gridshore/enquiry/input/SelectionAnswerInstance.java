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
package nl.gridshore.enquiry.input;

import nl.gridshore.enquiry.def.ChoiceDef;
import nl.gridshore.enquiry.def.MultipleChoiceQuestionDef;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Entity
public class SelectionAnswerInstance extends AnswerInstance {

    @ManyToMany
    @JoinTable(name = "chosen_answers")
    private List<ChoiceDef> choiceDefs = new ArrayList<ChoiceDef>();

    @ManyToOne
    private MultipleChoiceQuestionDef questionDef;

    protected SelectionAnswerInstance() {
    }

    public SelectionAnswerInstance(final MultipleChoiceQuestionDef questionDef, final ChoiceDef... choices) {
        this(questionDef, Arrays.asList(choices));
    }

    public SelectionAnswerInstance(final MultipleChoiceQuestionDef questionDef, final List<ChoiceDef> choices) {
        Assert.notNull(choices, "The choices parameter may not be null");
        Assert.notNull(questionDef, "The questionDef parameter may not be null");
        Assert.isTrue(fromSameQuestion(questionDef, choices), "Not all provided choices belong to the same question");

        this.questionDef = questionDef;
        choiceDefs.addAll(choices);
    }

    private boolean fromSameQuestion(final MultipleChoiceQuestionDef expectedQuestionDef, final List<ChoiceDef> choices) {
        for (ChoiceDef choice : choices) {
            if (!expectedQuestionDef.sameIdentityAs(choice.getQuestionDef())) {
                return false;
            }
        }
        return true;
    }

    public List<ChoiceDef> getChoiceDefs() {
        return Collections.unmodifiableList(choiceDefs);
    }

    @Override
    public MultipleChoiceQuestionDef getQuestionDef() {
        return questionDef;
    }

    public boolean isSelected(ChoiceDef choiceDef) {
        for (ChoiceDef def : choiceDefs) {
            if (def.equals(choiceDef)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getAsText() {
        StringBuilder sb = new StringBuilder();
        Iterator<ChoiceDef> iterator = choiceDefs.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().getText());
            if (iterator.hasNext()) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
