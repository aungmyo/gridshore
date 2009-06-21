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

package nl.gridshore.enquiry.web;

import nl.gridshore.enquiry.def.MultipleChoiceQuestionDef;
import nl.gridshore.enquiry.def.OpenQuestionDef;
import nl.gridshore.enquiry.def.QuestionDef;
import nl.gridshore.enquiry.input.EnquiryInstance;
import nl.gridshore.enquiry.input.OpenAnswerInstance;
import nl.gridshore.enquiry.input.SelectionAnswerInstance;
import nl.gridshore.enquiry.web.question.MultipleChoicePanel;
import nl.gridshore.enquiry.web.question.OpenQuestionPanel;
import nl.gridshore.enquiry.web.question.QuestionPanel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import java.util.ArrayList;
import java.util.List;

public class EnquiryPanel extends Panel {

    private EnquiryInstance enquiryInstance;
    private List<QuestionPanel> answerPanels = new ArrayList<QuestionPanel>();

    public EnquiryPanel(String id, EnquiryInstance enquiryInstance) {
        super(id);
        this.enquiryInstance = enquiryInstance;
        initializeQuestionPanels(enquiryInstance.getEnquiryDef().getQuestions());
    }

    private void initializeQuestionPanels(final List<QuestionDef> questions) {
        Form form = new Form("form");
        add(form);
        final RepeatingView questionPanels = new RepeatingView("questions");
        form.add(questionPanels);
        for (QuestionDef question : questions) {
            if (question instanceof MultipleChoiceQuestionDef) {
                final MultipleChoicePanel panel = new MultipleChoicePanel(questionPanels.newChildId(), (MultipleChoiceQuestionDef) question, (SelectionAnswerInstance) enquiryInstance.getAnswerForQuestion(question));
                questionPanels.add(panel);
                answerPanels.add(panel);
            } else if (question instanceof OpenQuestionDef) {
                final OpenQuestionPanel panel = new OpenQuestionPanel(questionPanels.newChildId(), (OpenQuestionDef) question, (OpenAnswerInstance) enquiryInstance.getAnswerForQuestion(question));
                questionPanels.add(panel);
                answerPanels.add(panel);
            }
        }

        form.add(new AjaxSubmitLink("submit") {
            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                System.out.println("formSubmitted");
                for (QuestionPanel panel : answerPanels) {
                    System.out.println(panel.getAnswer());
                }
            }
        });
    }
}
