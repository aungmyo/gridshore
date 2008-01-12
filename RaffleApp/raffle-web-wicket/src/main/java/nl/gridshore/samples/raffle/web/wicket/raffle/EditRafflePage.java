package nl.gridshore.samples.raffle.web.wicket.raffle;

import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Prize;
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

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 27, 2007
 * Time: 8:08:29 PM
 * Wicket implementation for the page that enables you to edit a raffle
 */
public class EditRafflePage extends BasePage {
    public EditRafflePage(final PageParameters pageParams) {
        final Raffle raffle = raffleService.giveRaffleById(pageParams.getLong(RaffleConstants.PARAM_RAFFLE_ID));

        add(new RaffleForm("raffle-form", raffle));
        add(new ParticipantsListView("participants", raffle.getParticipants(), pageParams));
        add(new ParticipantForm("participant-form", raffle, pageParams));
        add(new PrizesListView("prizes", raffle.getPrizes(), pageParams));
        add(new PrizeForm("prize-form", raffle, pageParams));
        add(new Label("page-title-label", "Edit raffle"));

    }

    private class RaffleForm extends Form {
        public RaffleForm(final String id, final Raffle raffle) {
            super(id);
            add(new Label("title-label", "Title"));
            add(new Label("description-label", "Description"));
            add(new TextField("raffle-title-input", new PropertyModel(raffle, "title")));
            add(new TextField("raffle-description-input", new PropertyModel(raffle, "description")));
            add(new Link("cancelraffle") {
                public void onClick() {
                    setResponsePage(AllRafflesPage.class);
                }
            });

            add(new Button("storeraffle") {
                public void onSubmit() {
                    raffleService.storeRaffle(raffle);
                    setResponsePage(AllRafflesPage.class);
                }
            });
        }
    }

    private class ParticipantForm extends Form {
        public ParticipantForm(final String id, final Raffle raffle, final PageParameters pageParams) {
            super(id);
            final Participant participant = new Participant();
            add(new Label("participant-name-label", "Participant name"));
            add(new TextField("participant-name", new PropertyModel(participant, "name")));
            add(new Button("addparticipant") {
                public void onSubmit() {
                    raffle.addParticipant(participant);
                    raffleService.storeRaffle(raffle);
                    setResponsePage(EditRafflePage.class, pageParams);
                }
            });
        }
    }

    private class PrizeForm extends Form {
        public PrizeForm(String id, final Raffle raffle, final PageParameters pageParams) {
            super(id);
            final Prize prize = new Prize();
            add(new Label("prize-title-label", "Prize title"));
            add(new Label("prize-description-label", "Prize description"));
            add(new TextField("prize-title", new PropertyModel(prize, "title")));
            add(new TextField("prize-description", new PropertyModel(prize, "description")));
            add(new Button("addprize") {
                public void onSubmit() {
                    raffle.addPrize(prize);
                    raffleService.storeRaffle(raffle);
                    setResponsePage(EditRafflePage.class, pageParams);
                }
            });
        }
    }

    private class ParticipantsListView extends ListView {
        private final PageParameters pageParams;

        public ParticipantsListView(String id, List list, PageParameters pageParams) {
            super(id, list);
            this.pageParams = pageParams;
        }

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
    }

    private class PrizesListView extends ListView {
        private final PageParameters pageParams;

        private PrizesListView(String id, List list, PageParameters pageParams) {
            super(id, list);
            this.pageParams = pageParams;
        }

        protected void populateItem(ListItem item) {
            final Prize prize = (Prize) item.getModelObject();
            item.add(new Label("title", prize.getTitle()));
            item.add(new Label("description", prize.getDescription()));
            item.add(new Link("delete-prize") {
                public void onClick() {
                    raffleService.removePrizeFromRaffle(prize);
                    setResponsePage(EditRafflePage.class, pageParams);
                }
            });
        }
    }
}
