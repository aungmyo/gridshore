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

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
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

    public SelectionAnswerInstance() {
    }

    public SelectionAnswerInstance(final MultipleChoiceQuestionDef questionDef) {
        this.questionDef = questionDef;
    }

    public List<ChoiceDef> getChoiceDefs() {
        return Collections.unmodifiableList(choiceDefs);
    }

    @Override
    public MultipleChoiceQuestionDef getQuestionDef() {
        return questionDef;
    }

    public boolean isChosen(ChoiceDef choiceDef) {
        for (ChoiceDef def : choiceDefs) {
            if (def.equals(choiceDef)) {
                return true;
            }
        }
        return false;
    }

    public void addChoice(ChoiceDef choiceDef) {
        if (!isChosen(choiceDef)) {
            choiceDefs.add(choiceDef);
        }
    }

    public void removeChoice(ChoiceDef choiceDef) {
        for (ChoiceDef def : choiceDefs) {
            if (def.equals(choiceDef)) {
                choiceDefs.remove(def);
                return;
            }
        }
    }

    @Override
    public String getText() {
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
