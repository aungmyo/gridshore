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

package nl.gridshore.enquiry.def;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class EnquiryDefTest {

    private EnquiryDef testSubject;

    @Before
    public void setUp() {
        this.testSubject = new EnquiryDef();
    }

    @Test
    public void testConstructor() {
        final ArrayList<QuestionDef> questionDefs = new ArrayList<QuestionDef>();
        final OpenQuestionDef questionDef = new OpenQuestionDef("");
        final OpenQuestionDef questionDef2 = new OpenQuestionDef("");
        questionDefs.add(questionDef);
        questionDefs.add(questionDef2);
        testSubject = new EnquiryDef("title", questionDefs);
        assertEquals("title", testSubject.getTitle());
        assertEquals(2, testSubject.getQuestions().size());
        assertSame(questionDef2, testSubject.getQuestions().get(1));
        assertEquals(1, questionDef2.getIndex());
        assertSame(testSubject, questionDef.getEnquiry());
        assertSame(testSubject, questionDef2.getEnquiry());
    }
}
