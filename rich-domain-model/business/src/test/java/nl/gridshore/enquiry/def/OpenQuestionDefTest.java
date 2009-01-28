package nl.gridshore.enquiry.def;

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
    public void testGettersAndSetters() {
        testSubject.setAnswerLength(AnswerLength.MULTILINE);
        assertSame(AnswerLength.MULTILINE, testSubject.getAnswerLength());
    }
}
