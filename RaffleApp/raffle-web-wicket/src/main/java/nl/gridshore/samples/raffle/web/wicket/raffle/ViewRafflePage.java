package nl.gridshore.samples.raffle.web.wicket.raffle;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Price;
import nl.gridshore.samples.raffle.domain.Raffle;
import nl.gridshore.samples.raffle.web.wicket.BasePage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 23, 2007
 * Time: 9:31:28 PM
 * Page showing a specific Raffle with all the participants and winners
 */
public class ViewRafflePage extends BasePage {
    @SpringBean
    RaffleService raffleService;

    public ViewRafflePage(final PageParameters pageParams) {
        add(new Label("page-title-label", "View raffle"));

        Raffle raffle = raffleService.giveRaffleById(pageParams.getLong(RaffleConstants.PARAM_RAFFLE_ID));
        add(new Label("title-label", "Raffle title"));
        add(new Label("title", raffle.getTitle()));
        add(new Label("description-label", "Raffle description"));
        add(new Label("description", raffle.getDescription()));
        add(new Label("participant-name-label", "Participant name"));
        add(new ListView("participants", raffle.getParticipants()) {
            protected void populateItem(ListItem item) {
                Participant participant = (Participant) item.getModelObject();
                item.add(new Label("name", participant.getName()));
            }
        });
        add(new Label("price-title-label", "Price title"));
        add(new Label("price-description-label", "Price description"));
        add(new ListView("prizes", raffle.getPrices()) {
            protected void populateItem(ListItem item) {
                Price price = (Price) item.getModelObject();
                item.add(new Label("price-title-value", price.getTitle()));
                item.add(new Label("price-description-value", price.getDescription()));
            }
        });
        add(new Link("back") {
            public void onClick() {
                setResponsePage(AllRafflesPage.class);
            }
        });
    }
}
