package nl.gridshore.samples.raffle.business.impl;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.business.Randomizer;
import nl.gridshore.samples.raffle.business.exceptions.ParticipantIsAWinnerException;
import nl.gridshore.samples.raffle.business.exceptions.PrizeDoesNotHaveAWinnerException;
import nl.gridshore.samples.raffle.business.exceptions.UnknownRaffleException;
import nl.gridshore.samples.raffle.business.exceptions.WinnerHasBeenSelectedException;
import nl.gridshore.samples.raffle.dao.ParticipantDao;
import nl.gridshore.samples.raffle.dao.PrizeDao;
import nl.gridshore.samples.raffle.dao.RaffleDao;
import nl.gridshore.samples.raffle.dao.WinnerDao;
import nl.gridshore.samples.raffle.dao.exceptions.EntityNotFoundException;
import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Prize;
import nl.gridshore.samples.raffle.domain.Raffle;
import nl.gridshore.samples.raffle.domain.Winner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 18, 2007
 * Time: 9:59:58 PM
 * Business service implementation for the RaffleService interface
 */
public class RaffleServiceImpl implements RaffleService {
    private RaffleDao raffleDao;
    private ParticipantDao participantDao;
    private PrizeDao prizeDao;
    private WinnerDao winnerDao;
    private Randomizer randomizer;

    public RaffleServiceImpl(
            RaffleDao raffleDao, ParticipantDao participantDao, PrizeDao prizeDao, WinnerDao winnerDao, Randomizer randomizer) {
        this.raffleDao = raffleDao;
        this.participantDao = participantDao;
        this.prizeDao = prizeDao;
        this.winnerDao = winnerDao;
        this.randomizer = randomizer;
    }

    public List<Raffle> giveAllRaffles() {
        return raffleDao.loadAll();
    }

    public Raffle giveRaffleById(Long raffleId) throws UnknownRaffleException {
        try {
            return raffleDao.loadById(raffleId);
        } catch (EntityNotFoundException e) {
            throw new UnknownRaffleException("Problem loading the raffle with provided id, it does not exist", e);
        }
    }

    public void storeRaffle(final Raffle raffle) {
        raffleDao.save(raffle);
    }

    public void removeRaffle(final Raffle raffle) {
        raffleDao.delete(raffle);
    }

    public void removeParticipantFromRaffle(final Participant participant) throws ParticipantIsAWinnerException {
        Raffle raffle = raffleDao.loadById(participant.getRaffle().getId());
        List<Prize> prizes = raffle.getPrizes();
        Participant foundPaticipant = participantDao.loadById(participant.getId());
        for (Prize prize : prizes) {
            if (prize.getWinner() != null && prize.getWinner().getParticipant().equals(foundPaticipant)) {
                throw new ParticipantIsAWinnerException("The provided participant (" +
                        foundPaticipant.getName() +
                        ")is a winner and cannot be deleted");
            }
        }
        raffle.removeParticipant(foundPaticipant);
        participantDao.delete(participant);
    }

    public void removePrizeFromRaffle(Prize prize) {
        Raffle raffle = raffleDao.loadById(prize.getRaffle().getId());
        Prize foundPrize = prizeDao.loadById(prize.getId());
        raffle.removePrize(foundPrize);
        prizeDao.delete(foundPrize);

    }

    public Prize chooseWinnerForPrize(Prize prize) throws WinnerHasBeenSelectedException {
        Prize foundPrize = prizeDao.loadById(prize.getId());
        if (foundPrize.getWinner() != null) {
            throw new WinnerHasBeenSelectedException("A winner has allready been selected for the prize : "
                    + foundPrize.getTitle() +
                    ". The current winner is : " + foundPrize.getWinner().getParticipant().getName());
        }

        List<Participant> participants = foundPrize.getRaffle().getParticipants();

        Integer randomNumber = randomizer.createRandomNumber(participants.size()) - 1; // -1 for starting at zero

        Winner winner = new Winner(foundPrize, participants.get(randomNumber));

        foundPrize.setWinner(winner);

        return foundPrize;
    }

    public void removeWinnerFromPrize(Prize prize) throws PrizeDoesNotHaveAWinnerException {
        Prize foundPrize = prizeDao.loadById(prize.getId());
        if (foundPrize.getWinner() == null) {
            throw new PrizeDoesNotHaveAWinnerException(
                    "No winner has been selected for the prize : " + foundPrize.getTitle());
        }
        winnerDao.delete(foundPrize.getWinner());
        foundPrize.setWinner(null);
    }

    public List<Participant> giveRandomParticipants(Raffle raffle, Integer numParticipants) {
        List<Participant> randomParticipants = new ArrayList<Participant>();
        Raffle foundRaffle = raffleDao.loadById(raffle.getId());
        List<Participant> participants = foundRaffle.getParticipants();
        for (int i = 0; i < numParticipants; i++) {
            Integer randomNumber = randomizer.createRandomNumber(participants.size()) - 1; // -1 for starting at zero
            randomParticipants.add(participants.get(randomNumber));
        }
        return randomParticipants;
    }
}
