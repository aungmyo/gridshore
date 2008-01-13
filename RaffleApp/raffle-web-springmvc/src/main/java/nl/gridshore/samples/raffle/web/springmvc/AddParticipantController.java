package nl.gridshore.samples.raffle.web.springmvc;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Raffle;
import nl.gridshore.samples.raffle.web.springmvc.validator.ParticipantValidator;
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
 * Time: 10:37:04 PM
 * Controller used to add a participant to the raffle
 */
@Controller
@RequestMapping("/addparticipant.view")
@SessionAttributes("participant")
public class AddParticipantController {
    private final RaffleService raffleService;
    private final ParticipantValidator participantValidator;

    @Autowired
    public AddParticipantController(RaffleService raffleService, ParticipantValidator participantValidator) {
        this.raffleService = raffleService;
        this.participantValidator = participantValidator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@RequestParam("raffleId")long raffleId, ModelMap model) {
        Raffle raffle = this.raffleService.giveRaffleById(raffleId);
        Participant participant = new Participant();
        raffle.addParticipant(participant);
        model.addAttribute(participant);
        return "participantForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("participant")Participant participant, BindingResult bindingResult, SessionStatus status) {
        participantValidator.validate(participant, bindingResult);
        if (bindingResult.hasErrors()) {
            return "participantForm";
        } else {
            this.raffleService.storeRaffle(participant.getRaffle());
            status.setComplete();
            return "redirect:editraffle.view?raffleId=" + participant.getRaffle().getId();
        }
    }

}
