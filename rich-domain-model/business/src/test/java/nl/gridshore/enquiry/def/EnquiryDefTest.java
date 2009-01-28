package nl.gridshore.enquiry.def;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EnquiryDefTest {

    private EnquiryDef testSubject;

    @Before
    public void setUp() {
        this.testSubject = new EnquiryDef();
    }

    @Test
    public void testAddAndRemoveQuestions() {
        assertEquals(0, testSubject.getQuestions().size());

        OpenQuestionDef mockQuestion = new OpenQuestionDef();
        assertNull(mockQuestion.getEnquiry());

        testSubject.appendQuestion(mockQuestion);
        assertSame(testSubject, mockQuestion.getEnquiry());
        assertEquals(1, mockQuestion.getIndex());

        testSubject.appendQuestion(mockQuestion);
        assertEquals(1, testSubject.getQuestions().size());
        assertSame(mockQuestion, testSubject.getQuestions().get(0));

        testSubject.insertQuestion(new SingleChoiceQuestionDef(), 0);
        assertEquals(2, testSubject.getQuestions().size());
        assertEquals(2, mockQuestion.getIndex());

        testSubject.removeQuestion(0);
        assertEquals(1, testSubject.getQuestions().size());
        assertSame(mockQuestion, testSubject.getQuestions().get(0));
        assertNull(testSubject.removeQuestionIfPresent(new OpenQuestionDef()));

        testSubject.removeQuestionIfPresent(mockQuestion);
        assertEquals(0, testSubject.getQuestions().size());
        assertNull(mockQuestion.getEnquiry());
    }

    @Test
    public void testGettersAndSetters() {
        testSubject.setTitle("title");
        assertEquals("title", testSubject.getTitle());
    }
}
