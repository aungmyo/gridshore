package nl.gridshore.samples.raffle.business.impl;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.business.exceptions.UnknownRaffleException;
import nl.gridshore.samples.raffle.dao.ParticipantDao;
import nl.gridshore.samples.raffle.dao.RaffleDao;
import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Raffle;

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

    public RaffleServiceImpl(RaffleDao raffleDao, ParticipantDao participantDao) {
        this.raffleDao = raffleDao;
        this.participantDao = participantDao;
    }

    public List<Raffle> giveAllRaffles() {
        return raffleDao.loadAll();
    }

    public Raffle giveRaffleById(Long raffleId) throws UnknownRaffleException {
        return raffleDao.loadById(raffleId);
    }

    public void storeRaffle(final Raffle raffle) {
        raffleDao.save(raffle);
    }

    public void removeRaffle(final Raffle raffle) {
        raffleDao.delete(raffle);
    }

    public void removeParticipantFromRaffle(final Participant participant) {
        Raffle raffle = raffleDao.loadById(participant.getRaffle().getId());
        Participant foundPaticipant = participantDao.loadById(participant.getId());
        raffle.removeParticipant(foundPaticipant);
        participantDao.delete(participant);
    }
}
