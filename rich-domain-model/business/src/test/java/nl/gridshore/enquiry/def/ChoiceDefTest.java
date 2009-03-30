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
}
