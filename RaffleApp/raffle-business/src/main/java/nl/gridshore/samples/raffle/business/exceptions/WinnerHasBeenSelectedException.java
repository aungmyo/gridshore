package nl.gridshore.samples.raffle.business.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 13, 2007
 * Time: 5:53:20 PM
 * Exception thrown when trying to assign a winner to a price that alredy has a winner
 */
public class WinnerHasBeenSelectedException extends RuntimeException {
    public WinnerHasBeenSelectedException(String s) {
        super(s);
    }
}
