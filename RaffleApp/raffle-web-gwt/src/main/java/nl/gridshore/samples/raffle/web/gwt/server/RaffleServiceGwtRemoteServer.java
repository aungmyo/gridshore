package nl.gridshore.samples.raffle.web.gwt.server;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Prize;
import nl.gridshore.samples.raffle.domain.Raffle;
import nl.gridshore.samples.raffle.web.gwt.client.RaffleServiceGwtRemote;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 14, 2007
 * Time: 9:00:33 PM
 * Google web toolkit implementation of the remote service to make contact with the spring business service.
 */
public class RaffleServiceGwtRemoteServer implements RaffleServiceGwtRemote {
    private RaffleService raffleService;

    public RaffleServiceGwtRemoteServer(RaffleService raffleService) {
        this.raffleService = raffleService;
    }

    public String getRandomName(String prizeDesc) {
        Prize prize = new Prize();
        prize.setTitle(prizeDesc);
        Prize foundPrize = raffleService.chooseWinnerForPrize(prize);
        return foundPrize.getWinner().getParticipant().getName();
    }

    public ArrayList getAllNames() {
        ArrayList participantNames = new ArrayList();
        for (Participant participant : getRaffle().getParticipants()) {
            participantNames.add(participant.getName());
        }
        return participantNames;
    }

    public void storeName(String name) {
        Participant participant = new Participant();
        participant.setName(name);

        Raffle raffle = getRaffle();
        raffle.addParticipant(participant);
        raffleService.storeRaffle(raffle);
    }

    public ArrayList getAllWinners() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private Raffle getRaffle() {
        List<Raffle> raffles = raffleService.giveAllRaffles();
        return raffles.get(0);
    }
}
