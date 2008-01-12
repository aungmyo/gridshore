package nl.gridshore.samples.raffle.dao.jpa;

import nl.gridshore.samples.raffle.dao.RaffleDao;
import nl.gridshore.samples.raffle.dao.exceptions.EntityNotFoundException;
import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Prize;
import nl.gridshore.samples.raffle.domain.Raffle;

import java.util.List;

public class RaffleDaoJpaTest extends BaseTestDaoJpa {
    private RaffleDao raffleDao;
    private static final long RAFFLE_ID_WITH_ALL = 1L;
    private static final long RAFFLE_ID_UNKNOWN = 999L;


    public void setRaffleDao(RaffleDao raffleDao) {
        this.raffleDao = raffleDao;
    }

    public void testLoadRaffle() {
        Raffle raffle = raffleDao.loadById(RAFFLE_ID_WITH_ALL);
        assertNotNull("A raffle should have been found", raffle);
        assertEquals("Name of raffle is not as expected", "Male raffle", raffle.getTitle());
        List<Prize> prizes = raffle.getPrizes();
        assertNotNull("The raffle should have prizes", prizes);
        assertEquals("The amount of prizes should be 2", 2, prizes.size());
        Prize prize = prizes.get(0);
        assertEquals("The found prize is not as expected", "Bier", prize.getTitle());
        assertNotNull("There should be a winner for this prize", prize.getWinner());
        assertNotNull("This winner should be a participant as well", prize.getWinner().getParticipant());
        assertEquals("There should be a winner with the right name for this prize", "Roy", prize.getWinner().getParticipant().getName());
    }

    public void testLoadUnknownRaffle() {
        try {
            raffleDao.loadById(RAFFLE_ID_UNKNOWN);
            fail("An EntityNotFoundException should have been thrown");
        } catch (EntityNotFoundException e) {
            // as expected
        }
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

    public void testFilterRafflesWildcart() {
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

    public void testAddParticipant() throws Exception {
        int countBefore = getJdbcTemplate().queryForInt("select count(*) from participants");
        Raffle raffle = raffleDao.loadById(RAFFLE_ID_WITH_ALL);
        Participant participant = new Participant();
        participant.setName("oke");
        raffle.addParticipant(participant);
        raffleDao.save(raffle);
        int count = getJdbcTemplate().queryForInt("select count(*) from participants");
        assertTrue("no participant is created", (countBefore + 1) == count);
    }

}
