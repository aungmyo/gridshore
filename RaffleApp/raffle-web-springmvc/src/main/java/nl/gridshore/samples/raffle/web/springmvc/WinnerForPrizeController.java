package nl.gridshore.samples.raffle.web.springmvc;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.domain.Prize;
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
 * This controller class is used to create a winner for a specified prize.
 */
@Controller
@RequestMapping("/addwinner.view")
public class WinnerForPrizeController {
    private final RaffleService raffleService;

    @Autowired
    public WinnerForPrizeController(RaffleService raffleService) {
        this.raffleService = raffleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String performAddWinner(@RequestParam("prizeId")long prizeId) {
        Prize prize = new Prize();
        prize.setId(prizeId);
        prize = this.raffleService.chooseWinnerForPrize(prize);
        return "redirect:editraffle.view?raffleId=" + prize.getRaffle().getId();
    }
}
