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
