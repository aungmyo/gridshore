package nl.gridshore.enquiry.def;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SingleChoiceQuestionDefTest {

    @Test
    public void testConstructor() {
        List<ChoiceDef> choiceDefs = new ArrayList<ChoiceDef>();
        final ChoiceDef choiceDef = new ChoiceDef();
        final ChoiceDef choiceDef2 = new ChoiceDef();
        choiceDefs.add(choiceDef);
        choiceDefs.add(choiceDef2);
        SingleChoiceQuestionDef testSubject = new SingleChoiceQuestionDef("Test", choiceDefs);

        assertEquals("Test", testSubject.getQuestionText());
        assertEquals(2, testSubject.getChoiceDefs().size());
        assertSame(choiceDef, testSubject.getChoiceDefs().get(0));
        assertEquals(1, choiceDef2.getIndex());
    }
}
