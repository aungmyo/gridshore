package nl.gridshore.samples.raffle.web.wicket;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 25, 2007
 * Time: 11:10:45 AM
 * Class used to store a menu item for showing on a page
 */
public class MenuItem implements Serializable {
    private String caption;
    private Class linkedPage;

    public MenuItem(String caption, Class linkedPage) {
        this.caption = caption;
        this.linkedPage = linkedPage;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Class getLinkedPage() {
        return linkedPage;
    }

    public void setLinkedPage(Class linkedPage) {
        this.linkedPage = linkedPage;
    }
}
