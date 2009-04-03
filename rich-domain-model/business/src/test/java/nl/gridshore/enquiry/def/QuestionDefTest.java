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

import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class QuestionDefTest {

    private QuestionDef testSubject;

    @Before
    public void setUp() {
        testSubject = new QuestionDef() {
        };
    }

    @Test
    public void testSetParentChoice() {
        final ChoiceDef choice = new ChoiceDef();
        testSubject.setParentChoiceDef(choice);

        try {
            testSubject.setEnquiry(new EnquiryDef());
            fail("Expected exception");
        }
        catch (IllegalArgumentException ex) {

        }
        testSubject.setParentChoiceDef(null);
        testSubject.setEnquiry(new EnquiryDef());
        try {
            testSubject.setParentChoiceDef(new ChoiceDef());
            fail("Expected exception");
        }
        catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void testGetEnquiry_ViaParentChoice() {
        final EnquiryDef mockEnquiry = new EnquiryDef();
        final ChoiceDef choice = createMock(ChoiceDef.class);
        expect(choice.getEnquiry()).andReturn(mockEnquiry);
        replay(choice);
        testSubject.setParentChoiceDef(choice);
        assertSame(mockEnquiry, testSubject.getEnquiry());
        verify(choice);
    }

    @Test
    public void testGetEnquiry_Directly() {
        final EnquiryDef mockEnquiry = new EnquiryDef();
        testSubject.setEnquiry(mockEnquiry);
        assertSame(mockEnquiry, testSubject.getEnquiry());
    }

    @Test
    public void testGetAndSetIndex() {
        assertEquals(0, testSubject.getIndex());
        testSubject.setIndex(432);
        assertEquals(432, testSubject.getIndex());
    }
}
