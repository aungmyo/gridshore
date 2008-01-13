package nl.gridshore.samples.raffle.business;

import nl.gridshore.samples.raffle.business.exceptions.InputParameterException;
import nl.gridshore.samples.raffle.business.impl.BasicRandomizer;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Dec 13, 2007
 * Time: 1:29:29 PM
 * TestCase for the basic randomizer
 */
public class BasicRandomizerTest {
    private static BasicRandomizer randomizer = new BasicRandomizer();

    @Test
    public void callRandomizerWithOnlyOneItem() {
        int randomNumber = randomizer.createRandomNumber(1);
        assertEquals(1, randomNumber);
    }

    @Test(expected = InputParameterException.class)
    public void callRandomizerWithInvalidMaxValue() {
        randomizer.createRandomNumber(0);
    }
}
