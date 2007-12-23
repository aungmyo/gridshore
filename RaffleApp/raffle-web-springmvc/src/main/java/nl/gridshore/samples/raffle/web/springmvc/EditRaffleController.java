package nl.gridshore.samples.raffle.web.springmvc;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.domain.Raffle;
import nl.gridshore.samples.raffle.web.springmvc.validator.RaffleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 22, 2007
 * Time: 8:39:28 PM
 * Controller class for handling the editing of a raffle
 */
@Controller
@RequestMapping("/editraffle.view")
@SessionAttributes("raffle")
public class EditRaffleController {
    private final RaffleService raffleService;

    @Autowired
    public EditRaffleController(RaffleService raffleService) {
        this.raffleService = raffleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@RequestParam("raffleId")long raffleId, ModelMap model) {
        Raffle raffle = this.raffleService.giveRaffleById(raffleId);
        model.addAttribute(raffle);
        return "raffleForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("raffle")Raffle raffle, BindingResult bindingResult, SessionStatus status) {
        new RaffleValidator().validate(raffle, bindingResult);
        if (bindingResult.hasErrors()) {
            return "raffleForm";
        } else {
            this.raffleService.storeRaffle(raffle);
            status.setComplete();
            return "redirect:welcome.view";
        }
    }
}
