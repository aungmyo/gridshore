package nl.gridshore.enquiry.def;

import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class QuestionDefTest {

    private QuestionDef testSubject;

    @Before
    public void setUp() {
        testSubject = new QuestionDef() {
        };
    }

    @Test
    public void testSetParentChoice() {
        final ChoiceDef choice = new ChoiceDef();
        testSubject.setParentChoiceDef(choice);

        try {
            testSubject.setEnquiry(new EnquiryDef());
            fail("Expected exception");
        }
        catch (IllegalArgumentException ex) {

        }
        testSubject.setParentChoiceDef(null);
        testSubject.setEnquiry(new EnquiryDef());
        try {
            testSubject.setParentChoiceDef(new ChoiceDef());
            fail("Expected exception");
        }
        catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void testGetEnquiry_ViaParentChoice() {
        final EnquiryDef mockEnquiry = new EnquiryDef();
        final ChoiceDef choice = createMock(ChoiceDef.class);
        expect(choice.getEnquiry()).andReturn(mockEnquiry);
        replay(choice);
        testSubject.setParentChoiceDef(choice);
        assertSame(mockEnquiry, testSubject.getEnquiry());
        verify(choice);
    }

    @Test
    public void testGetEnquiry_Directly() {
        final EnquiryDef mockEnquiry = new EnquiryDef();
        testSubject.setEnquiry(mockEnquiry);
        assertSame(mockEnquiry, testSubject.getEnquiry());
    }

    @Test
    public void testGetAndSetIndex() {
        assertEquals(0, testSubject.getIndex());
        testSubject.setIndex(432);
        assertEquals(432, testSubject.getIndex());
    }
}
