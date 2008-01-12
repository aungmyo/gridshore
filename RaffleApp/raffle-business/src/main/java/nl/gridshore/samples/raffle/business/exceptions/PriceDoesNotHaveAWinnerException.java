package nl.gridshore.samples.raffle.business.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 10, 2008
 * Time: 9:31:49 PM
 * Thrown if a winner is removed from a price that does not have a winner yet
 */
public class PriceDoesNotHaveAWinnerException extends RaffleBusinessException {
    public PriceDoesNotHaveAWinnerException(String s) {
        super(s);
    }
}
