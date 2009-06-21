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
import nl.gridshore.enquiry.input.SelectionAnswerInstance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceAnswerDto implements Serializable {

    private static final long serialVersionUID = -5481923681838396503L;

    private List<ChoiceDef> choices = new ArrayList<ChoiceDef>();

    public MultipleChoiceAnswerDto() {
    }

    public MultipleChoiceAnswerDto(SelectionAnswerInstance answer) {
        if (answer != null) {
            choices.addAll(answer.getChoiceDefs());
        }
    }

    public List<ChoiceDef> getChoices() {
        return choices;
    }

    public void setChoices(final List<ChoiceDef> choices) {
        this.choices = choices;
    }
}
