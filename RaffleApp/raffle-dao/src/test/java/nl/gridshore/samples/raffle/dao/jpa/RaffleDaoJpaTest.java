package nl.gridshore.samples.raffle.dao.jpa;

import nl.gridshore.samples.raffle.domain.Price;
import nl.gridshore.samples.raffle.domain.Raffle;
import org.springframework.test.jpa.AbstractJpaTests;

import java.util.List;

public class RaffleDaoJpaTest extends AbstractJpaTests {
    private RaffleDaoJpa raffleDao;

    public void setRaffleDao(RaffleDaoJpa raffleDao) {
        this.raffleDao = raffleDao;
    }

    public void testLoadRaffle() {
        Raffle raffle = raffleDao.loadById(1L);
        assertNotNull("A raffle should have been found", raffle);
        assertEquals("Name of raffle is not as expected", "Male raffle", raffle.getTitle());
        List<Price> prices = raffle.getPrices();
        assertNotNull("The raffle should have prices", prices);
        assertEquals("The amount of prices should be 2", 2, prices.size());
        Price price = prices.get(0);
        assertEquals("The found price is not as expected", "Bier", price.getTitle());
        assertNotNull("There should be a winner for this price", price.getWinner());
        assertNotNull("This winner should be a participant as well", price.getWinner().getParticipant());
        assertEquals("There should be a winner with the right name for this price", "Roy", price.getWinner().getParticipant().getName());
    }

    public void testFilterRaffles() {
        Raffle filter = new Raffle();
        filter.setTitle("Male raffle");
        List<Raffle> raffles = raffleDao.loadByFilter(filter);
        assertNotNull("A list with one raffle should have been found", raffles);
        assertEquals("Exactly one raffle should have been found", 1, raffles.size());
        Raffle raffle = raffles.get(0);
        assertEquals("Not the right raffle was returned", new Long(1), raffle.getId());
    }

    public void testFilterRafflesWildcrt() {
        Raffle filter = new Raffle();
        filter.setTitle("raffle");
        List<Raffle> raffles = raffleDao.loadByFilter(filter);
        assertNotNull("A list with raffles should have been found", raffles);
        assertEquals("Exactly two raffles should have been found", 2, raffles.size());
    }

    public void testStoreRaffle() {
        int numRaffles = raffleDao.loadAll().size();
        Raffle newRaffle = new Raffle();
        newRaffle.setTitle("Test raffle");
        newRaffle.setDescription("Description of test raffle");
        raffleDao.save(newRaffle);
        assertEquals("Inserting a new Raffle did not succeed", numRaffles + 1, raffleDao.loadAll().size());
    }

    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-dao-spring.xml", "classpath:dao-config.xml"};
    }
}
