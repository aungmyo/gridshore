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
        final OpenQuestionDef questionDef = new OpenQuestionDef("", AnswerLength.MULTILINE);
        final OpenQuestionDef questionDef2 = new OpenQuestionDef("", AnswerLength.MULTILINE);
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
