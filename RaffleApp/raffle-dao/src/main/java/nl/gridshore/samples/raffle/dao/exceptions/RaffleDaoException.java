package nl.gridshore.samples.raffle.dao.exceptions;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 10, 2008
 * Time: 10:09:57 PM
 * Base exception for all dao related raffle exceptions
 */
public class RaffleDaoException extends RuntimeException {
    public RaffleDaoException(String s) {
        super(s);
    }

    public RaffleDaoException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
