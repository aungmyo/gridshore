package nl.gridshore.enquiry.def;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MultipleChoiceQuestionDefTest {

    private MultipleChoiceQuestionDef testSubject;

    @Before
    public void setUp() {
        this.testSubject = new MultipleChoiceQuestionDef();
    }

    @Test
    public void testInsertAndAppendChoices() {
        assertEquals(0, testSubject.getChoices().size());
        ChoiceDef choice1 = new ChoiceDef("");
        testSubject.appendChoice(choice1);
        assertEquals(1, testSubject.getChoices().size());
        assertEquals(1, choice1.getIndex());

        testSubject.insertChoice(choice1, 1);
        assertEquals(1, testSubject.getChoices().size());
        assertEquals(1, choice1.getIndex());

        testSubject.insertChoice(new ChoiceDef(""), 0);
        assertEquals(2, testSubject.getChoices().size());
        assertEquals(2, choice1.getIndex());
    }

    @Test
    public void testRemoveChoices() {
        ChoiceDef choice1 = new ChoiceDef("");
        testSubject.appendChoice(choice1);
        testSubject.insertChoice(new ChoiceDef(""), 0);
        assertEquals(2, testSubject.getChoices().size());

        testSubject.removeChoice(0);
        assertEquals(1, choice1.getIndex());

        assertNull(testSubject.removeChoiceIfPresent(new ChoiceDef()));

        assertSame(choice1, testSubject.removeChoiceIfPresent(choice1));
        assertNull(choice1.getEnquiry());
    }
}
