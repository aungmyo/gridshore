package nl.gridshore.samples.raffle.web.wicket.raffle;

import nl.gridshore.samples.raffle.domain.Price;
import nl.gridshore.samples.raffle.domain.Raffle;
import nl.gridshore.samples.raffle.web.wicket.BasePage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 3, 2007
 * Time: 9:06:25 PM
 * Page used to actually to a raffle.
 */
public class DoTheRafflePage extends BasePage {
    public DoTheRafflePage(final PageParameters pageParams) {
        Raffle raffle = new Raffle();
        add(new Label("page-title-label", "Do The Raffle"));
        add(new ChooseRaffleForm("choiceraffle", raffle));


        add(new Label("price-title-label", "Price title"));
        add(new Label("price-description-label", "Price description"));
        add(new Label("price-winner-label", "winner"));
        add(new Label("price-choose-winner-label", "choose winner"));
        Raffle selectedRaffle;
        if (pageParams.containsKey(RaffleConstants.PARAM_RAFFLE_ID)) {
            Long raffleId = pageParams.getLong(RaffleConstants.PARAM_RAFFLE_ID);
            selectedRaffle = raffleService.giveRaffleById(raffleId);
        } else {
            selectedRaffle = new Raffle();
        }

        add(new ListView("prizes", selectedRaffle.getPrices()) {
            protected void populateItem(ListItem item) {
                final Price price = (Price) item.getModelObject();
                item.add(new Label("price-title-value", price.getTitle()));
                item.add(new Label("price-description-value", price.getDescription()));

                String winner = "";
                if (price.getWinner() != null) {
                    winner = price.getWinner().getParticipant().getName();
                }
                item.add(new Label("price-winner-value", winner));
//                item.add(new Label("price-choose-winner-value", "choose me"));
                item.add(new Link("price-choose-winner-value") {
                    public void onClick() {
                        raffleService.chooseWinnerForPrice(price);
                        setResponsePage(DoTheRafflePage.class, pageParams);
                    }
                });
            }
        });
    }

    private class ChooseRaffleForm extends Form {
        private ChooseRaffleFormModel raffleModel;

        public ChooseRaffleForm(final String id, final Raffle raffle) {
            super(id);
            this.raffleModel = new ChooseRaffleFormModel(raffle);
            List<Raffle> raffles = raffleService.giveAllRaffles();
            final DropDownChoice raffleDropdown =
                    new DropDownChoice("raffleChoice", new PropertyModel(raffleModel, "raffle"), raffles, new ChoiceRenderer("title", "id"));
            add(raffleDropdown);

        }

        protected void onSubmit() {
            PageParameters putPageParams = new PageParameters();
            if (raffleModel.getRaffle() != null) {
                putPageParams.add(RaffleConstants.PARAM_RAFFLE_ID, raffleModel.getRaffle().getId().toString());
            }
            setResponsePage(DoTheRafflePage.class, putPageParams);
        }
    }

    private class ChooseRaffleFormModel implements Serializable {
        private Raffle raffle;

        private ChooseRaffleFormModel(Raffle raffle) {
            this.raffle = raffle;
        }

        public Raffle getRaffle() {
            return raffle;
        }

        public void setRaffle(Raffle raffle) {
            this.raffle = raffle;
        }
    }

}
