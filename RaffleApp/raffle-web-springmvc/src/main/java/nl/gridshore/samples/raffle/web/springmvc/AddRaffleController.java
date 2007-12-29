package nl.gridshore.samples.raffle.web.springmvc;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.domain.Raffle;
import nl.gridshore.samples.raffle.web.springmvc.validator.RaffleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 29, 2007
 * Time: 9:51:18 AM
 * Controller used to add a raffle
 */
@Controller
@RequestMapping("/addraffle.view")
@SessionAttributes("raffle")
public class AddRaffleController {
    private final RaffleService raffleService;

    @Autowired
    public AddRaffleController(RaffleService raffleService) {
        this.raffleService = raffleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(ModelMap model) {
        Raffle raffle = new Raffle();
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
            return "redirect:index.html";
        }
    }

}
