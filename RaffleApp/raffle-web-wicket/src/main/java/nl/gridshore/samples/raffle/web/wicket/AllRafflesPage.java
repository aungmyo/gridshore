package nl.gridshore.samples.raffle.web.wicket;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.domain.Raffle;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 19, 2007
 * Time: 8:44:05 PM
 * Page used to show all raffle items.
 */
public class AllRafflesPage extends BasePage {
    @SpringBean
    RaffleService raffleService;

    public AllRafflesPage() {
        add(new ListView("raffles", raffleService.giveAllRaffles()) {

            protected void populateItem(ListItem item) {
                Raffle raffle = (Raffle) item.getModelObject();
                item.add(new Link("view", item.getModel()) {
                    public void onClick() {
                        Raffle raffle = (Raffle) getModelObject();
                        setResponsePage(new ViewRafflePage(raffle));
                    }
                });
                item.add(new Label("title", raffle.getTitle()));
                item.add(new Label("description", raffle.getDescription()));
            }
        });
    }
}
