package nl.gridshore.samples.raffle.dao.jpa;

import nl.gridshore.samples.raffle.domain.Raffle;
import org.springframework.test.AbstractTransactionalSpringContextTests;

public class RaffleDaoJpaTest extends AbstractTransactionalSpringContextTests {
    private RaffleDaoJpa raffleDao;

    public void setRaffleDao(RaffleDaoJpa raffleDao) {
        this.raffleDao = raffleDao;
    }

    public void testStoreRaffle() {
        Raffle newRaffle = new Raffle();
        newRaffle.setTitle("Test raffle");
        newRaffle.setDescription("Description of test raffle");
        raffleDao.save(newRaffle);
    }

    protected String[] getConfigLocations() {
        return new String[] {"classpath:dao-config.xml"};
    }
}
