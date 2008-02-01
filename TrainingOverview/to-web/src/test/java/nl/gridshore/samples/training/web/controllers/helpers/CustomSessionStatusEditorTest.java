package nl.gridshore.samples.training.web.controllers.helpers;

import nl.gridshore.samples.training.web.controllers.training.helpers.CustomSessionStatusEditor;
import nl.gridshore.samples.training.domain.SessionStatus;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 29, 2008
 * Time: 9:08:41 PM
 * Test class for the CustomSessionStatusEditor
 */
public class CustomSessionStatusEditorTest {

    @Test
    public void setAsText() {
        CustomSessionStatusEditor customSessionStatusEditor = new CustomSessionStatusEditor();
        customSessionStatusEditor.setAsText("cancelled");
    }
}
