package nl.gridshore.samples.raffle.web.springmvc;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.domain.Prize;
import nl.gridshore.samples.raffle.domain.Raffle;
import nl.gridshore.samples.raffle.web.springmvc.validator.PrizeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 23, 2007
 * Time: 8:34:54 AM
 * Controller class to a add a prize to a raffle
 */
@Controller
@RequestMapping("/addprize.view")
@SessionAttributes("prize")
public class AddPrizeController {
    private final RaffleService raffleService;

    @Autowired
    public AddPrizeController(RaffleService raffleService) {
        this.raffleService = raffleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@RequestParam("raffleId")long raffleId, ModelMap model) {
        Raffle raffle = this.raffleService.giveRaffleById(raffleId);
        Prize prize = new Prize();
        raffle.addPrize(prize);
        model.addAttribute(prize);
        return "prizeForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("prize")Prize prize, BindingResult bindingResult, SessionStatus status) {
        new PrizeValidator().validate(prize, bindingResult);
        if (bindingResult.hasErrors()) {
            return "prizeForm";
        } else {
            this.raffleService.storeRaffle(prize.getRaffle());
            status.setComplete();
            return "redirect:editraffle.view?raffleId=" + prize.getRaffle().getId();
        }
    }
}
