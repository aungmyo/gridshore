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

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@DiscriminatorValue("MULTIPLE")
public class MultipleChoiceQuestionDef extends QuestionDef {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionDef")
    @OrderBy("index")
    protected List<ChoiceDef> choices;

    public MultipleChoiceQuestionDef() {
        this.choices = new ArrayList<ChoiceDef>();
    }

    public List<ChoiceDef> getChoices() {
        return Collections.unmodifiableList(choices);
    }

    public void appendChoice(ChoiceDef choiceDef) {
        insertChoice(choiceDef, choices.size());
    }

    public void insertChoice(ChoiceDef choiceDef, int index) {
        removeChoiceIfPresent(choiceDef);
        choiceDef.setQuestionDef(this);
        choices.add(Math.min(index, choices.size()), choiceDef);
        recalculateIndices();
    }

    public ChoiceDef removeChoiceIfPresent(ChoiceDef choiceDef) {
        if (choices.remove(choiceDef)) {
            recalculateIndices();
            return choiceDef;
        }
        return null;
    }

    public ChoiceDef removeChoice(int index) {
        return removeChoiceIfPresent(choices.get(index));
    }

    // ======================== Helper methods ==============================

    private void recalculateIndices() {
        int t = 1;
        for (ChoiceDef choice : choices) {
            choice.setIndex(t++);
        }
    }
}
