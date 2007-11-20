package nl.gridshore.samples.raffle.web.wicket;

import nl.gridshore.samples.raffle.business.RaffleService;
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
        int numRaffles = raffleService.giveAllRaffles().size();
//        add(new Label("numraffles-label","Number of raffles : " +numRaffles));
    }
}
