package nl.gridshore.samples.raffle.web.wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 19, 2007
 * Time: 8:37:43 PM
 * BasePage used to get a consistent look and feel.
 */
public class BasePage extends WebPage {
    public BasePage() {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem("Home", HomePage.class));
        menuItems.add(new MenuItem("All raffles", AllRafflesPage.class));
        ListView menuItemsComponent = new ListView("menu", menuItems) {

            protected void populateItem(ListItem item) {
                MenuItem menuItem = (MenuItem) item.getModelObject();
                BookmarkablePageLink link = new BookmarkablePageLink("link", menuItem.getLinkedPage());
                link.add(new Label("caption", menuItem.getCaption()));
                item.add(link);
            }
        };
        add(menuItemsComponent);
    }
}
