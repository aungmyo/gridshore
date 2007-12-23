package nl.gridshore.samples.raffle.web.springmvc;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.domain.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 23, 2007
 * Time: 9:03:24 AM
 * This controller class is used to create a winner for a specified price.
 */
@Controller
@RequestMapping("/addwinner.view")
public class WinnerForPriceController {
    private final RaffleService raffleService;

    @Autowired
    public WinnerForPriceController(RaffleService raffleService) {
        this.raffleService = raffleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String performAddWinner(@RequestParam("priceId")long priceId) {
        Price price = new Price();
        price.setId(priceId);
        price = this.raffleService.chooseWinnerForPrice(price);
        return "redirect:editraffle.view?raffleId=" + price.getRaffle().getId();
    }
}
