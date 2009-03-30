package nl.gridshore.enquiry.input;

import nl.gridshore.enquiry.def.ChoiceDef;
import nl.gridshore.enquiry.def.MultipleChoiceQuestionDef;
import nl.gridshore.rdm.persistence.BaseEntity;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SelectionAnswerInstanceTest {

    private SelectionAnswerInstance testSubject;
    private MultipleChoiceQuestionDef question;
    private ChoiceDef choice1;
    private ChoiceDef choice2;

    @Before
    public void setUp() throws NoSuchFieldException {
        List<ChoiceDef> choices = new ArrayList<ChoiceDef>();
        choice1 = new ChoiceDef("Choice 1");
        choice2 = new ChoiceDef("Choice 2");
        choices.add(choice1);
        choices.add(choice2);
        question = new MultipleChoiceQuestionDef("Make a choice", choices);
        final Field idField = BaseEntity.class.getDeclaredField("id");
        ReflectionUtils.makeAccessible(idField);
        ReflectionUtils.setField(idField, question, 1L);
    }

    @Test
    public void testNoArgConstructor() {
        testSubject = new SelectionAnswerInstance();
        assertNotNull("ChoiceDefs should be initialized as empty list", testSubject.getChoiceDefs());
        assertNull(testSubject.getQuestionDef());
    }

    @Test
    public void testConstructor_ChoiceDefList() {
        List<ChoiceDef> choices = new ArrayList<ChoiceDef>();
        choices.add(choice1);

        testSubject = new SelectionAnswerInstance(question, choices);
        assertEquals(1, testSubject.getChoiceDefs().size());
        assertSame(question, testSubject.getQuestionDef());
    }

    @Test
    public void testConstructor_ChoiceDefVararg() {
        testSubject = new SelectionAnswerInstance(question, choice1);
        assertEquals(1, testSubject.getChoiceDefs().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_WrongChoice() {
        ChoiceDef otherQuestionChoice = new ChoiceDef("Text");
        testSubject = new SelectionAnswerInstance(question, otherQuestionChoice);
    }

    @Test
    public void testIsSelected() {
        testSubject = new SelectionAnswerInstance(question, choice1);
        assertTrue(testSubject.isSelected(choice1));
        assertFalse(testSubject.isSelected(choice2));
    }

    @Test
    public void testGetAsText() {
        testSubject = new SelectionAnswerInstance(question, choice1, choice2);
        assertEquals("Choice 1,Choice 2", testSubject.getAsText());
    }
}
