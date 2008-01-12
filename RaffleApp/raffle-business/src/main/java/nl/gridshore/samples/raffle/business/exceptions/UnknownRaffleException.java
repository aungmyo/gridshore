package nl.gridshore.samples.raffle.business.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 18, 2007
 * Time: 9:37:28 PM
 * Runtime exception thrown if we could not find the raffle while we should have found it.
 */
public class UnknownRaffleException extends RaffleBusinessException {
    public UnknownRaffleException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
