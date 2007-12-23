package nl.gridshore.samples.raffle.web.springmvc;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.domain.Price;
import nl.gridshore.samples.raffle.domain.Raffle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 23, 2007
 * Time: 8:29:40 AM
 * Controller used to delete a price
 */
@Controller
@RequestMapping("/deleteprice.view")
@SessionAttributes("raffle")
public class DeletePriceController {
    private final RaffleService raffleService;

    @Autowired
    public DeletePriceController(RaffleService raffleService) {
        this.raffleService = raffleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String performDelete(@ModelAttribute("raffle")Raffle raffle,
                                @RequestParam("priceId")long priceId) {
        Price price = new Price();
        price.setId(priceId);
        price.setRaffle(raffle);
        this.raffleService.removePriceFromRaffle(price);
        return "redirect:editraffle.view?raffleId=" + raffle.getId();
    }

}
