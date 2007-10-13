package nl.gridshore.samples.raffle;

import junit.framework.TestCase;
import nl.gridshore.samples.raffle.server.RaffleService;
import nl.gridshore.samples.raffle.server.RaffleServiceStringFileBased;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 10-okt-2007
 * Time: 20:41:27
 * Test case for the RaffleService implementation(s)
 */
public class RaffleServiceTest extends TestCase {
    private RaffleService<String> raffleService;

    protected void setUp() throws Exception {
        raffleService = new RaffleServiceStringFileBased("d:/personnames.txt","d:/winners.txt");
    }

    public void testAddItem() {
        raffleService.addItem("Jettro Coenradie");
    }
}
