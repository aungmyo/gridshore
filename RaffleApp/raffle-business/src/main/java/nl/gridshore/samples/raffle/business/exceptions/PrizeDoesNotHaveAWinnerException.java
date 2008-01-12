package nl.gridshore.samples.raffle.business.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 10, 2008
 * Time: 9:31:49 PM
 * Thrown if a winner is removed from a prize that does not have a winner yet
 */
public class PrizeDoesNotHaveAWinnerException extends RaffleBusinessException {
    public PrizeDoesNotHaveAWinnerException(String s) {
        super(s);
    }
}
