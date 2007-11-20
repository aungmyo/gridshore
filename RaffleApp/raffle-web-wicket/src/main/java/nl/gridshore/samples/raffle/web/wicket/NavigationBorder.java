package nl.gridshore.samples.raffle.web.wicket;

import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.border.BoxBorder;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 19, 2007
 * Time: 11:05:40 PM
 * Navigation component consisting of the navigation and the content items
 */
public class NavigationBorder extends Border {
    public NavigationBorder(final String id) {
        super(id);
        add(new BoxBorder("navigationBorder"));
        add(new BoxBorder("bodyBorder"));
    }
}
