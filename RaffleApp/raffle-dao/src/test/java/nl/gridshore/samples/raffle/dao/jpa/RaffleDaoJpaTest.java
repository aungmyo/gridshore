package nl.gridshore.samples.raffle.dao.jpa;

import nl.gridshore.samples.raffle.domain.Raffle;
import org.springframework.test.jpa.AbstractJpaTests;

public class RaffleDaoJpaTest extends AbstractJpaTests {
    private RaffleDaoJpa raffleDao;

    public void setRaffleDao(RaffleDaoJpa raffleDao) {
        this.raffleDao = raffleDao;
    }

    public void testStoreRaffle() {
        int numRaffles = raffleDao.loadAll().size();
        Raffle newRaffle = new Raffle();
        newRaffle.setTitle("Test raffle");
        newRaffle.setDescription("Description of test raffle");
        raffleDao.save(newRaffle);
        assertEquals("Inserting a new Raffle did not succeed",numRaffles+1,raffleDao.loadAll().size());
    }

    protected String[] getConfigLocations() {
        return new String[] {"classpath:dao-config.xml", "classpath:test-dao-spring.xml"};
    }
}
