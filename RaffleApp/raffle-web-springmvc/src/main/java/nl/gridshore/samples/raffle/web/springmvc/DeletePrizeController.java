package nl.gridshore.samples.raffle.web.springmvc;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.domain.Prize;
import nl.gridshore.samples.raffle.domain.Raffle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 23, 2007
 * Time: 8:29:40 AM
 * Controller used to delete a prize
 */
@Controller
@RequestMapping("/deleteprize.view")
@SessionAttributes("raffle")
public class DeletePrizeController {
    private final RaffleService raffleService;

    @Autowired
    public DeletePrizeController(RaffleService raffleService) {
        this.raffleService = raffleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String performDelete(@ModelAttribute("raffle")Raffle raffle,
                                @RequestParam("prizeId")long prizeId) {
        Prize prize = new Prize();
        prize.setId(prizeId);
        prize.setRaffle(raffle);
        this.raffleService.removePrizeFromRaffle(prize);
        return "redirect:editraffle.view?raffleId=" + raffle.getId();
    }

}
