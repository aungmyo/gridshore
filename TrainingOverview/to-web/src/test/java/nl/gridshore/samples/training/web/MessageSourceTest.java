package nl.gridshore.samples.training.web;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 26, 2008
 * Time: 3:46:49 PM
 * Test class for MessageSource thingies
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-web.xml"})
public class MessageSourceTest {
    private MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Test
    public void getMessageFromSource() {
        String result =
                messageSource.getMessage("Training.name[length.min].training.name",new Object[] {new Object(),3}, Locale.getDefault());
        assertEquals("Resource string not as expected","Name has  minimal length of 3", result);
    }
}
