package nl.gridshore.samples.raffle.business.impl;

import nl.gridshore.samples.raffle.business.Randomizer;
import nl.gridshore.samples.raffle.business.exceptions.InputParameterException;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 13, 2007
 * Time: 1:22:29 PM
 * This is the most basic randomizer you can think of. It uses the java ranoom method with the miliseconds as a seed
 */
public class BasicRandomizer implements Randomizer {
    Random random = new Random(System.currentTimeMillis());

    public Integer createRandomNumber(Integer maxValue) throws InputParameterException {
        if (maxValue < 1) {
            throw new InputParameterException("maxValue for random number must be higher than 0");
        }
        return (Math.abs(random.nextInt()) % (maxValue)) + 1;
    }
}
