package nl.gridshore.samples.raffle.business;

import junit.framework.TestCase;
import nl.gridshore.samples.raffle.business.impl.RaffleServiceImpl;
import nl.gridshore.samples.raffle.dao.ParticipantDao;
import nl.gridshore.samples.raffle.dao.PriceDao;
import nl.gridshore.samples.raffle.dao.RaffleDao;
import nl.gridshore.samples.raffle.domain.Raffle;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 18, 2007
 * Time: 10:20:51 PM
 * test case for testing the business service RaffleService using mock objects for dataaccess components
 */
public class RaffleServiceTest extends TestCase {
    private RaffleDao mockRaffleDao;
    private ParticipantDao mockParticipantDao;
    private PriceDao mockPriceDao;

    private RaffleService raffleService;

    protected void setUp() throws Exception {
        mockRaffleDao = createMock(RaffleDao.class);
        mockParticipantDao = createMock(ParticipantDao.class);
        mockPriceDao = createMock(PriceDao.class);
        raffleService = new RaffleServiceImpl(mockRaffleDao, mockParticipantDao, mockPriceDao);
    }

    public void testGiveAllRaffles() {
        Raffle dummyRaffle = new Raffle();
        dummyRaffle.setId(1L);
        dummyRaffle.setTitle("dummy title");
        List<Raffle> dummyRaffles = new ArrayList<Raffle>();
        dummyRaffles.add(dummyRaffle);
        expect(mockRaffleDao.loadAll()).andReturn(dummyRaffles);
        replay(mockRaffleDao);
        List<Raffle> raffles = raffleService.giveAllRaffles();
        assertNotNull("The raffles list should not be null", raffles);
        assertEquals("Number of returned raffles not as expected", 1, raffles.size());
        verify(mockRaffleDao);
    }
}
