package nl.gridshore.samples.raffle.web.wicket.raffle;

import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Raffle;
import nl.gridshore.samples.raffle.web.wicket.BasePage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 27, 2007
 * Time: 8:51:12 PM
 * Remove the raffle from the from the storage
 */
public class DeleteRafflePage extends BasePage {
    public DeleteRafflePage(PageParameters pageParams) {
        super();
        add(new Label("page-title-label", "View raffle"));

        final Raffle raffle = raffleService.giveRaffleById(pageParams.getLong(RaffleConstants.PARAM_RAFFLE_ID));
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

        Form raffleForm = new Form("form");
        raffleForm.add(new Link("cancelraffle") {
            public void onClick() {
                setResponsePage(AllRafflesPage.class);
            }
        });
        raffleForm.add(new Link("deleteraffle") {
            public void onClick() {
                raffleService.removeRaffle(raffle);
                setResponsePage(AllRafflesPage.class);
            }
        });
        add(raffleForm);
    }
}
