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
        add(new Label("page-title-label", "Raffles"));
        createNewRaffleForm();
        createListOfRaffles();
    }

    private void createNewRaffleForm() {
        Form form = new Form("form");
        final Raffle raffle = new Raffle();
        form.add(new TextField("raffle-title-input", new PropertyModel(raffle, "title")));
        form.add(new TextField("raffle-description-input", new PropertyModel(raffle, "description")));

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
        add(form);
    }

    private void createListOfRaffles() {
        add(new Label("raffle-title-label", "Title"));
        add(new Label("raffle-description-label", "Description"));
        add(new ListView("raffles", raffleService.giveAllRaffles()) {

            protected void populateItem(ListItem item) {
                Raffle raffle = (Raffle) item.getModelObject();
                PageParameters pageParams = new PageParameters();
                pageParams.add(RaffleConstants.PARAM_RAFFLE_ID, raffle.getId().toString());
                item.add(new BookmarkablePageLink("view", ViewRafflePage.class, pageParams));
                item.add(new BookmarkablePageLink("edit", EditRafflePage.class, pageParams));
                item.add(new BookmarkablePageLink("delete", DeleteRafflePage.class, pageParams));
                item.add(new Label("raffle-title-value", raffle.getTitle()));
                item.add(new Label("raffle-description-value", raffle.getDescription()));
            }
        });
    }
}
