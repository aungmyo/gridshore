package nl.gridshore.samples.raffle.web.springmvc;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Raffle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 22, 2007
 * Time: 11:15:01 PM
 * Controller used to delete a participant from a raffle
 */
@Controller
@RequestMapping("/deleteparticipant.view")
@SessionAttributes("raffle")
public class DeleteParticipantController {
    private final RaffleService raffleService;

    @Autowired
    public DeleteParticipantController(RaffleService raffleService) {
        this.raffleService = raffleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String performDelete(@ModelAttribute("raffle")Raffle raffle,
                                @RequestParam("participantId")long participantId) {
        Participant participant = new Participant();
        participant.setId(participantId);
        participant.setRaffle(raffle);
        this.raffleService.removeParticipantFromRaffle(participant);
        return "redirect:editraffle.view?raffleId=" + raffle.getId();
    }
}
