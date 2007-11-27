package nl.gridshore.samples.raffle.business;

import nl.gridshore.samples.raffle.business.exceptions.UnknownRaffleException;
import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Raffle;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 18, 2007
 * Time: 9:32:46 PM
 * Business service exposing all raffle related services.
 */
public interface RaffleService {
    /**
     * Returns  list with al available raffles
     *
     * @return List containing all raffles.
     */
    List<Raffle> giveAllRaffles();

    /**
     * Return the raffle belonging to the specified id, throws an Unknown raffle exception
     * if the id does not exist
     *
     * @param raffleId Long representing the id of a raffle instance
     * @return Raffle found belonging to the provided id
     * @throws nl.gridshore.samples.raffle.business.exceptions.UnknownRaffleException
     *          when the raffle for
     *          provided id does not exist.
     */
    Raffle giveRaffleById(Long raffleId) throws UnknownRaffleException;

    /**
     * Store the provided raffle to be able to obtain it later on
     *
     * @param raffle Raffle object to store
     */
    void storeRaffle(Raffle raffle);

    /**
     * Removes the raffle from the storage
     *
     * @param raffle Raffle to remove
     */
    void removeRaffle(Raffle raffle);

    /**
     * Remove a specific participant
     *
     * @param participant Participant to remove
     */
    void removeParticipantFromRaffle(Participant participant);
}
