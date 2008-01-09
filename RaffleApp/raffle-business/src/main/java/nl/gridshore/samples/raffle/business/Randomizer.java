package nl.gridshore.samples.raffle.business;

import nl.gridshore.samples.raffle.business.exceptions.InputParameterException;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 13, 2007
 * Time: 6:35:17 AM
 * Generalization for our Randomizer implementations
 */
public interface Randomizer {
    /**
     * Returns a number starting with zero and ending before the provided max value
     *
     * @param maxValue Integer representing the maximum value for the random number
     * @return the created random number
     * @throws nl.gridshore.samples.raffle.business.exceptions.InputParameterException
     *          thrown when the maxValue is  smaller than 1
     */
    Integer createRandomNumber(Integer maxValue) throws InputParameterException;
}
