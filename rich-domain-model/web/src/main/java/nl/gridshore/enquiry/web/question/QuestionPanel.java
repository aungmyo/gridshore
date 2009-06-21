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

import nl.gridshore.enquiry.def.QuestionDef;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public abstract class QuestionPanel extends Panel {

    public QuestionPanel(String id, QuestionDef question) {
        super(id);
        add(new Label("questionNumber", question.getPath()));
        add(new Label("questionText", question.getQuestionText()));
    }

    public abstract AnswerEntry getAnswer();
}
