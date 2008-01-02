package nl.gridshore.samples.raffle.web.springmvc;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 2, 2008
 * Time: 12:25:51 PM
 * Controller used to select a(nother) raffle and price to obtain a winner for.
 */
@Controller
public class ChooseWinnerController {
    private RaffleService raffleService;

    @Autowired
    public ChooseWinnerController(RaffleService raffleService) {
        this.raffleService = raffleService;
    }

    @RequestMapping("/winner.view")
    public String createRaffleOverview(@RequestParam(value = "raffle_id", required = false)String raffleId, ModelMap model) {
        if (raffleId == null) {
            raffleId = "";
        }
        model.addAttribute("raffleId", raffleId);
        model.addAttribute("availableRaffles", raffleService.giveAllRaffles());
        return "winnerForm";
    }

    @RequestMapping("/winnerprizes.view")
    public String createPrizesOverview(@RequestParam("raffle_id")long raffleId, ModelMap model) {
        model.addAttribute(raffleService.giveRaffleById(raffleId));
        return "chooseprizeforraffle";
    }

    @RequestMapping("/pickwinner.view")
    public String createWinnerForPrize(@RequestParam("prizeId")long prizeId, ModelMap model) {
        Price price = new Price();
        price.setId(prizeId);
        price = this.raffleService.chooseWinnerForPrice(price);
        model.addAttribute(price);

        List<Participant> randomParticipants = this.raffleService.giveRandomParticipants(price.getRaffle(), 5);
        StringBuilder notWinners = new StringBuilder();
        for (Participant randomParticipant : randomParticipants) {
            notWinners.append("'").append(randomParticipant.getName()).append("'").append(",");
        }
        notWinners.deleteCharAt(notWinners.length() - 1);
        System.out.println(notWinners.toString());
        model.addAttribute("notwinners", notWinners.toString());
        return "andthewinneris";
    }
}
