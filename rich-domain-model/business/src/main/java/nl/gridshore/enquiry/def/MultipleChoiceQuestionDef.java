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
import javax.persistence.Inheritance;
import java.util.List;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("MULTIPLE")
@Inheritance
public class MultipleChoiceQuestionDef extends QuestionDef {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionDef")
    @OrderBy("index")
    private List<ChoiceDef> choices;

    public MultipleChoiceQuestionDef() {
        this.choices = new ArrayList<ChoiceDef>();
    }

    public List<ChoiceDef> getChoices() {
        return choices;
    }

    public void appendChoice(ChoiceDef choiceDef) {
        choices.add(choiceDef);
    }

    public void insertChoice(ChoiceDef choiceDef, int index) {
        choices.add(index, choiceDef);
    }
}
