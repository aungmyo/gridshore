package nl.gridshore.samples.raffle.web.springmvc;

import nl.gridshore.samples.raffle.business.RaffleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 21, 2007
 * Time: 8:35:10 PM
 * Controller class for all raffle related activities
 */
@Controller
public class RaffleController {
    private RaffleService raffleService;

    @Autowired
    public RaffleController(RaffleService raffleService) {
        this.raffleService = raffleService;
    }

    @RequestMapping("/raffles.view")
    public ModelMap rafflesHandler() {
        return new ModelMap("raffleList", raffleService.giveAllRaffles());
    }
}
