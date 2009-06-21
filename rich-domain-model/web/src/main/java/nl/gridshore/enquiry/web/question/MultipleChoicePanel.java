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

package nl.gridshore.enquiry.web.question;

import nl.gridshore.enquiry.def.ChoiceDef;
import nl.gridshore.enquiry.def.MultipleChoiceQuestionDef;
import nl.gridshore.enquiry.input.SelectionAnswerInstance;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.model.util.CollectionModel;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoicePanel extends QuestionPanel {

    private List<ChoiceDef> choices = new ArrayList<ChoiceDef>();
    private String questionPath;

    public MultipleChoicePanel(String id, MultipleChoiceQuestionDef question, final SelectionAnswerInstance answer) {
        super(id, question);
        questionPath = question.getPath();
        if (answer != null) {
            choices = answer.getChoiceDefs();
        }
        CheckBoxMultipleChoice<ChoiceDef> choices = new CheckBoxMultipleChoice<ChoiceDef>(
                "choices",
                new CollectionModel<ChoiceDef>(this.choices),
                question.getChoiceDefs(),
                new ChoiceRenderer<ChoiceDef>("text"));
        add(choices);
    }

    @Override
    public AnswerEntry getAnswer() {
        StringBuilder sb = new StringBuilder();
        for (ChoiceDef choice : choices) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(choice.getText());
        }
        return new AnswerEntry(questionPath, sb.toString());
    }
}
