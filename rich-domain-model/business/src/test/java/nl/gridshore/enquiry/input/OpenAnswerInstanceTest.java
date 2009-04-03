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

package nl.gridshore.enquiry.input;

import nl.gridshore.enquiry.def.AnswerLength;
import nl.gridshore.enquiry.def.OpenQuestionDef;
import static org.junit.Assert.*;
import org.junit.Test;

public class OpenAnswerInstanceTest {

    private OpenAnswerInstance testSubject;

    @Test
    public void testConstructor() {
        OpenQuestionDef question1 = new OpenQuestionDef("What's the question", AnswerLength.MULTILINE);
        testSubject = new OpenAnswerInstance(question1, "My answer text");

        assertEquals("My answer text", testSubject.getAsText());
        assertSame(question1, testSubject.getQuestionDef());
    }

    @Test
    public void testNoArgConstructor() {
        testSubject = new OpenAnswerInstance();

        assertNull("Value should be initialized at null", testSubject.getAsText());
        assertNull("Value should be initialized at null", testSubject.getQuestionDef());
    }
}
