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

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ChoiceDefTest {

    private ChoiceDef testSubject;

    @Before
    public void setUp() {
        testSubject = new ChoiceDef();
    }

    @Test
    public void testGetEnquiry() {
        final EnquiryDef mockEnquiry = new EnquiryDef();
        assertNull(testSubject.getEnquiry());
        final MultipleChoiceQuestionDef questionDef = new MultipleChoiceQuestionDef() {
            @Override
            public EnquiryDef getEnquiry() {
                return mockEnquiry;
            }
        };
        testSubject.setQuestionDef(questionDef);

        assertSame(questionDef, testSubject.getQuestionDef());
        assertSame(mockEnquiry, testSubject.getEnquiry());
    }

    @Test
    public void testConstructor_TextOnly() {
        testSubject = new ChoiceDef("Testing");
        assertEquals("Testing", testSubject.getText());
    }


    @Test
    public void testConstructor_TextAndQuestions() {
        List<QuestionDef> subQuestions = new ArrayList<QuestionDef>();
        subQuestions.add(new OpenQuestionDef());
        subQuestions.add(new OpenQuestionDef());
        testSubject = new ChoiceDef("Testing", subQuestions);
        assertEquals("Testing", testSubject.getText());
        assertEquals(2, testSubject.getSubQuestions().size());
        assertEquals(1, testSubject.getSubQuestions().get(1).getIndex());
        assertEquals(1, testSubject.getSubQuestions().get(1).getIndex());
    }

    @Test
    public void testFindQuestionByPath() {
        List<QuestionDef> subQuestions = new ArrayList<QuestionDef>();
        subQuestions.add(new QuestionDef() {
            @Override
            QuestionDef getSubQuestionByPath(final int[] path) {
                assertEquals(2, path.length);
                assertEquals(1, path[0]);
                assertEquals(2, path[1]);
                return new OpenQuestionDef("This is the one");
            }
        });
        subQuestions.add(new OpenQuestionDef());
        testSubject = new ChoiceDef("Testing", subQuestions);

        QuestionDef actualResult = testSubject.getSubQuestionByPath(new int[]{0, 1, 2});

        assertEquals("This is the one", actualResult.getQuestionText());
    }
}
