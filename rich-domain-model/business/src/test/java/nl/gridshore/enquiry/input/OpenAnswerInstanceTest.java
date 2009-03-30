package nl.gridshore.enquiry.input;

import nl.gridshore.enquiry.def.AnswerLength;
import nl.gridshore.enquiry.def.OpenQuestionDef;
import static org.junit.Assert.*;
import org.junit.Test;

public class OpenAnswerInstanceTest {

    private OpenAnswerInstance testSubject;

    @Test
    public void testConstructor() {
        OpenQuestionDef question1 = new OpenQuestionDef("What's the question", AnswerLength.MULTILINE);
        testSubject = new OpenAnswerInstance(question1, "My answer text");

        assertEquals("My answer text", testSubject.getAsText());
        assertSame(question1, testSubject.getQuestionDef());
    }

    @Test
    public void testNoArgConstructor() {
        testSubject = new OpenAnswerInstance();

        assertNull("Value should be initialized at null", testSubject.getAsText());
        assertNull("Value should be initialized at null", testSubject.getQuestionDef());
    }
}
