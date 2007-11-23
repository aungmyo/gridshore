package nl.gridshore.samples.raffle.web.wicket;

import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Raffle;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 23, 2007
 * Time: 9:31:28 PM
 * Page showing a specific Raffle with all the participants and winners
 */
public class ViewRafflePage extends BasePage {
    public ViewRafflePage(final Raffle raffle) {
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
    }
}
