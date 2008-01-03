package nl.gridshore.samples.raffle.business.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 3, 2008
 * Time: 11:48:54 PM
 * Exception thrown if a participant is deleted while being a winner
 */
public class ParticipantIsAWinnerException extends RuntimeException {
    public ParticipantIsAWinnerException(String s) {
        super(s);
    }
}
