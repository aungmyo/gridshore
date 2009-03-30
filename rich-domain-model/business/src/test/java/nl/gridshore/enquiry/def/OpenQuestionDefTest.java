package nl.gridshore.enquiry.def;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;

public class OpenQuestionDefTest {

    private OpenQuestionDef testSubject;

    @Before
    public void setUp() {
        testSubject = new OpenQuestionDef();
    }

    @Test
    public void testConstructor() {
        testSubject = new OpenQuestionDef("Text", AnswerLength.MULTILINE);
        assertEquals("Text", testSubject.getQuestionText());
        assertSame(AnswerLength.MULTILINE, testSubject.getAnswerLength());
    }
}
