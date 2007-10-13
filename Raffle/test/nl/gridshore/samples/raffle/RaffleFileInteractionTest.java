package nl.gridshore.samples.raffle;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

import nl.gridshore.samples.raffle.server.RaffleFileInteraction;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 10-okt-2007
 * Time: 21:10:18
 * To change this template use File | Settings | File Templates.
 */
public class RaffleFileInteractionTest extends TestCase {
    RaffleFileInteraction personsFile;

    protected void setUp() throws Exception {
        personsFile = new RaffleFileInteraction("d:/persons.ser");
        
    }

    public void testAll() throws IOException, ClassNotFoundException {
        personsFile.storeItem("Jettro Coenradie");
        List<String> persons = personsFile.loadItems();
        System.out.println("persons "+persons.size());
        assertTrue(persons.size() > 0);
    }
}
