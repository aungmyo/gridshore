package nl.gridshore.samples.raffle.web.wicket.raffle;

import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Price;
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
        add(new PricesListView("prices", raffle.getPrices(), pageParams));
        add(new PriceForm("price-form", raffle, pageParams));
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

    private class PriceForm extends Form {
        public PriceForm(String id, final Raffle raffle, final PageParameters pageParams) {
            super(id);
            final Price price = new Price();
            add(new Label("price-title-label", "Price title"));
            add(new Label("price-description-label", "Price description"));
            add(new TextField("price-title", new PropertyModel(price, "title")));
            add(new TextField("price-description", new PropertyModel(price, "description")));
            add(new Button("addprice") {
                public void onSubmit() {
                    raffle.addPrice(price);
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

    private class PricesListView extends ListView {
        private final PageParameters pageParams;

        private PricesListView(String id, List list, PageParameters pageParams) {
            super(id, list);
            this.pageParams = pageParams;
        }

        protected void populateItem(ListItem item) {
            final Price price = (Price) item.getModelObject();
            item.add(new Label("title", price.getTitle()));
            item.add(new Label("description", price.getDescription()));
            item.add(new Link("delete-price") {
                public void onClick() {
                    raffleService.removePriceFromRaffle(price);
                    setResponsePage(EditRafflePage.class, pageParams);
                }
            });
        }
    }
}
