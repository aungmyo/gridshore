package nl.gridshore.samples.raffle.web.wicket;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.domain.Raffle;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 23, 2007
 * Time: 10:42:33 PM
 * Page with a form for entering information about a raffle.
 */
public class RaffleFormPage extends BasePage {
    @SpringBean
    RaffleService raffleService;

    public RaffleFormPage() {
        Form form = new Form("form");
        add(form);
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
    }
}
