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

import nl.gridshore.enquiry.def.EnquiryDef;
import nl.gridshore.enquiry.def.QuestionDef;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class AnswerInstanceTest {

    private AnswerInstance testSubject;

    @Test
    public void testGetAndSetEnquiryInstance() {
        testSubject = new AnswerInstance() {
            @Override
            public QuestionDef getQuestionDef() {
                fail("This method call was not expected in this test");
                return null;
            }

            @Override
            public String getAsText() {
                fail("This method call was not expected in this test");
                return null;
            }
        };

        assertNull("EnquiryDef should be initialized as null on AnswerInstance", testSubject.getEnquiryInstance());
        final EnquiryInstance enquiryInstance = new EnquiryInstance();
        testSubject.setEnquiryInstance(enquiryInstance);
        assertSame("The enquiry instance should be the same instance as the on that was set",
                   enquiryInstance,
                   testSubject.getEnquiryInstance());
    }

    @Test
    public void testGetEnquiryDef() {
        final EnquiryDef enquiryDef = new EnquiryDef("", new ArrayList<QuestionDef>());
        final QuestionDef mockQuestionDef = createMock(QuestionDef.class);
        expect(mockQuestionDef.getEnquiry()).andReturn(enquiryDef);
        replay(mockQuestionDef);
        testSubject = new AnswerInstance() {
            @Override
            public QuestionDef getQuestionDef() {
                return mockQuestionDef;
            }

            @Override
            public String getAsText() {
                return "blabla";
            }
        };

        assertSame(enquiryDef, testSubject.getEnquiryDef());
        verify(mockQuestionDef);
    }
}
