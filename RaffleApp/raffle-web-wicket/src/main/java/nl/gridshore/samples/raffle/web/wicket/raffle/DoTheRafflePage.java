package nl.gridshore.samples.raffle.web.wicket.raffle;

import nl.gridshore.samples.raffle.domain.Prize;
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


        add(new Label("prize-title-label", "Prize title"));
        add(new Label("prize-description-label", "Prize description"));
        add(new Label("prize-winner-label", "winner"));
        add(new Label("prize-choose-winner-label", "choose winner"));
        Raffle selectedRaffle;
        if (pageParams.containsKey(RaffleConstants.PARAM_RAFFLE_ID)) {
            Long raffleId = pageParams.getLong(RaffleConstants.PARAM_RAFFLE_ID);
            selectedRaffle = raffleService.giveRaffleById(raffleId);
        } else {
            selectedRaffle = new Raffle();
        }

        add(new ListView("prizes", selectedRaffle.getPrizes()) {
            protected void populateItem(ListItem item) {
                final Prize prize = (Prize) item.getModelObject();
                item.add(new Label("prize-title-value", prize.getTitle()));
                item.add(new Label("prize-description-value", prize.getDescription()));

                String winner = "";
                if (prize.getWinner() != null) {
                    winner = prize.getWinner().getParticipant().getName();
                }
                item.add(new Label("prize-winner-value", winner));
                item.add(new Link("prize-choose-winner-value") {
                    public void onClick() {
                        raffleService.chooseWinnerForPrize(prize);
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
