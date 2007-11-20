package nl.gridshore.samples.raffle.web.wicket;

import org.apache.wicket.markup.html.WebPage;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 19, 2007
 * Time: 8:37:43 PM
 * BasePage used to get a consistent look and feel.
 */
public class BasePage extends WebPage {
    public BasePage() {
        add(new NavigationBorder("navigationBorder"));
    }
}
