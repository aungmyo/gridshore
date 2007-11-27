package nl.gridshore.samples.raffle.web.wicket.raffle;

import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Raffle;
import nl.gridshore.samples.raffle.web.wicket.BasePage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 27, 2007
 * Time: 8:08:29 PM
 * Wicket implementation for the page that enables you to edit a raffle
 */
public class EditRafflePage extends BasePage {
    public EditRafflePage(final PageParameters pageParams) {
        Form raffleForm = new Form("raffle-form");
        final Raffle raffle = raffleService.giveRaffleById(pageParams.getLong(RaffleConstants.PARAM_RAFFLE_ID));
        raffleForm.add(new TextField("raffle-title-input", new PropertyModel(raffle, "title")));
        raffleForm.add(new TextField("raffle-description-input", new PropertyModel(raffle, "description")));
        raffleForm.add(new Link("cancelraffle") {
            public void onClick() {
                setResponsePage(AllRafflesPage.class);
            }
        });

        raffleForm.add(new Button("storeraffle") {
            public void onSubmit() {
                raffleService.storeRaffle(raffle);
                setResponsePage(AllRafflesPage.class);
            }
        });
        add(raffleForm);

        add(new Label("page-title-label", "Edit raffle"));

        raffleForm.add(new Label("title-label", "Raffle title"));
        raffleForm.add(new Label("description-label", "Raffle description"));

        add(new Label("participant-name-label", "Participant name"));
        add(new ListView("participants", raffle.getParticipants()) {
            protected void populateItem(ListItem item) {
                final Participant participant = (Participant) item.getModelObject();
                item.add(new Label("name", participant.getName()));
                item.add(new Link("delete-participant") {
                    public void onClick() {
                        raffleService.removeParticipantFromRaffle(participant);
                        setResponsePage(EditRafflePage.class, pageParams);
                    }
                });
            }
        });

        final Participant participant = new Participant();
        Form participantForm = new Form("participant-form");
        participantForm.add(new TextField("participant-name", new PropertyModel(participant, "name")));
        participantForm.add(new Button("addparticipant") {
            public void onSubmit() {
                raffle.addParticipant(participant);
                raffleService.storeRaffle(raffle);
                setResponsePage(EditRafflePage.class, pageParams);
            }
        });
        add(participantForm);
    }
}
