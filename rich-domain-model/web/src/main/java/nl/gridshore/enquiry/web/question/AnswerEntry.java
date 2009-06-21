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

import java.io.Serializable;

public class AnswerEntry implements Serializable {

    private static final long serialVersionUID = 1843915707495753862L;

    private String questionPath;
    private String answer;

    public AnswerEntry(final String questionPath, final String answer) {
        this.questionPath = questionPath;
        this.answer = answer;
    }

    public String getQuestionPath() {
        return questionPath;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return questionPath + ": " + answer;
    }
}
