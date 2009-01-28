package nl.gridshore.enquiry.def;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
        testSubject.setQuestionDef(new MultipleChoiceQuestionDef() {
            @Override
            public EnquiryDef getEnquiry() {
                return mockEnquiry;
            }
        });

        assertSame(mockEnquiry, testSubject.getEnquiry());
    }

    @Test
    public void testAppendAndInsertSubQuestions() {
        assertEquals(0, testSubject.getSubQuestions().size());
        QuestionDef mockQuestion = new QuestionDef() {
        };
        testSubject.appendSubQuestion(mockQuestion);
        assertEquals(1, testSubject.getSubQuestions().size());
        assertSame(testSubject, mockQuestion.parentChoiceDef);
        assertEquals(1, mockQuestion.getIndex());

        testSubject.insertSubQuestion(new OpenQuestionDef(), 0);
        assertEquals(2, testSubject.getSubQuestions().size());
        assertEquals(2, mockQuestion.getIndex());

        testSubject.insertSubQuestion(mockQuestion, 0);
        assertEquals(2, testSubject.getSubQuestions().size());
        assertEquals(1, mockQuestion.getIndex());
    }

    @Test
    public void testRemoveSubQuestions() {
        QuestionDef mockQuestion = new QuestionDef() {
        };
        testSubject.appendSubQuestion(mockQuestion);
        testSubject.appendSubQuestion(new OpenQuestionDef());
        assertEquals(2, testSubject.getSubQuestions().size());

        assertNull(testSubject.removeSubQuestionIfPresent(new OpenQuestionDef()));
        assertEquals(2, testSubject.getSubQuestions().size());

        assertNotNull(testSubject.removeSubQuestion(1));
        assertNotNull(testSubject.removeSubQuestionIfPresent(mockQuestion));
        assertEquals(0, testSubject.getSubQuestions().size());

    }

    @Test
    public void testGettersAndSetters() {
        testSubject = new ChoiceDef("Testing");
        assertEquals("Testing", testSubject.getText());

        assertEquals(0, testSubject.getIndex());
        testSubject.setIndex(3);
        assertEquals(3, testSubject.getIndex());
    }
}
