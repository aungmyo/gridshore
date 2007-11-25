package nl.gridshore.samples.raffle.web.wicket.raffle;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.domain.Raffle;
import nl.gridshore.samples.raffle.web.wicket.BasePage;
import nl.gridshore.samples.raffle.web.wicket.HomePage;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
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
        add(createNewRaffleForm());
        add(createListOfRaffles());
    }

    private Form createNewRaffleForm() {
        Form form = new Form("form");
        final Raffle raffle = new Raffle();
        form.add(new TextField("raffle-title", new PropertyModel(raffle, "title")));
        form.add(new TextField("raffle-description", new PropertyModel(raffle, "description")));

        form.add(new Link("cancel") {
            public void onClick() {
                setResponsePage(HomePage.class);
            }
        });

        form.add(new Button("storeraffle") {
            public void onSubmit() {
                raffleService.storeRaffle(raffle);
                setResponsePage(AllRafflesPage.class);
            }
        });

        return form;
    }

    private ListView createListOfRaffles() {
        return new ListView("raffles", raffleService.giveAllRaffles()) {

            protected void populateItem(ListItem item) {
                Raffle raffle = (Raffle) item.getModelObject();
                PageParameters pageParams = new PageParameters();
                pageParams.add(RaffleConstants.PARAM_RAFFLE_ID, raffle.getId().toString());
                item.add(new BookmarkablePageLink("view", ViewRafflePage.class, pageParams));
                item.add(new Label("title", raffle.getTitle()));
                item.add(new Label("description", raffle.getDescription()));
            }
        };
    }
}
