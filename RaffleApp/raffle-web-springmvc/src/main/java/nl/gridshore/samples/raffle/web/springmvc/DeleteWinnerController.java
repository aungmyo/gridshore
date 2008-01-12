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
 * Date: Jan 12, 2008
 * Time: 8:20:36 AM
 * Controller class to delete the winner for a prize
 */
@Controller
@RequestMapping("/deletewinner.view")
@SessionAttributes("raffle")
public class DeleteWinnerController {
    private final RaffleService raffleService;

    @Autowired
    public DeleteWinnerController(RaffleService raffleService) {
        this.raffleService = raffleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String performDelete(@ModelAttribute("raffle")Raffle raffle, @RequestParam("prizeId")long prizeId) {
        Prize prize = new Prize();
        prize.setId(prizeId);
        raffleService.removeWinnerFromPrize(prize);
        return "redirect:editraffle.view?raffleId=" + raffle.getId();
    }

}
