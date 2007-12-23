package nl.gridshore.samples.raffle.web.springmvc;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.domain.Price;
import nl.gridshore.samples.raffle.domain.Raffle;
import nl.gridshore.samples.raffle.web.springmvc.validator.PriceValidator;
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
 * Controller class to a add a price to a raffle
 */
@Controller
@RequestMapping("/addprice.view")
@SessionAttributes("price")
public class AddPriceController {
    private final RaffleService raffleService;

    @Autowired
    public AddPriceController(RaffleService raffleService) {
        this.raffleService = raffleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@RequestParam("raffleId")long raffleId, ModelMap model) {
        Raffle raffle = this.raffleService.giveRaffleById(raffleId);
        Price price = new Price();
        raffle.addPrice(price);
        model.addAttribute(price);
        return "priceForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("price")Price price, BindingResult bindingResult, SessionStatus status) {
        new PriceValidator().validate(price, bindingResult);
        if (bindingResult.hasErrors()) {
            return "priceForm";
        } else {
            this.raffleService.storeRaffle(price.getRaffle());
            status.setComplete();
            return "redirect:editraffle.view?raffleId=" + price.getRaffle().getId();
        }
    }
}
