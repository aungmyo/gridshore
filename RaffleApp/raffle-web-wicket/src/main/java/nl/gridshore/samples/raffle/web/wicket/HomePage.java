package nl.gridshore.samples.raffle.web.wicket;

import org.apache.wicket.markup.html.basic.Label;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 19, 2007
 * Time: 8:41:58 PM
 * Homepage wicket class.
 */
public class HomePage extends BasePage {
    public HomePage() {
        add(new Label("homelabel", "This is the homepage"));
    }
}
