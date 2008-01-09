package nl.gridshore.samples.raffle.business;

import junit.framework.TestCase;
import nl.gridshore.samples.raffle.business.exceptions.ParticipantIsAWinnerException;
import nl.gridshore.samples.raffle.business.exceptions.WinnerHasBeenSelectedException;
import nl.gridshore.samples.raffle.business.impl.RaffleServiceImpl;
import nl.gridshore.samples.raffle.dao.ParticipantDao;
import nl.gridshore.samples.raffle.dao.PriceDao;
import nl.gridshore.samples.raffle.dao.RaffleDao;
import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Price;
import nl.gridshore.samples.raffle.domain.Raffle;
import nl.gridshore.samples.raffle.domain.Winner;
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
    private PriceDao mockPriceDao;
    private Randomizer mockRandomizer;
    private ParticipantDao mockParticipantDao;

    private RaffleService raffleService;

    protected void setUp() throws Exception {
        mockRaffleDao = createMock(RaffleDao.class);
        mockParticipantDao = createMock(ParticipantDao.class);
        mockPriceDao = createMock(PriceDao.class);
        mockRandomizer = createMock(Randomizer.class);
        raffleService = new RaffleServiceImpl(mockRaffleDao, mockParticipantDao, mockPriceDao, mockRandomizer);
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

    public void testChooseWinner() {
        Price inputPrize = new Price();
        inputPrize.setId(3L);
        Price foundPrize = createFoundPrize(inputPrize);

        expect(mockPriceDao.loadById(inputPrize.getId())).andReturn(foundPrize);
        expect(mockRandomizer.createRandomNumber(foundPrize.getRaffle().getParticipants().size())).andReturn(1);

        replay(mockPriceDao, mockRandomizer, mockRaffleDao);

        Price priceForWinner = raffleService.chooseWinnerForPrice(inputPrize);

        verify(mockPriceDao, mockRandomizer, mockRaffleDao);
        assertNotNull("The returned price is null", priceForWinner);
        assertNotNull("winner is not stored, the price did not contain a winner", priceForWinner.getWinner());
    }

    private Price createFoundPrize(Price inputPrice) {
        Price foundPrice = new Price();
        foundPrice.setId(inputPrice.getId());
        foundPrice.setDescription("Price for testing");
        Raffle foundRaffle = new Raffle();
        foundRaffle.setId(1L);
        foundRaffle.setDescription("Raffle for testing");
        foundRaffle.addPrice(foundPrice);
        Participant jettro = new Participant();
        jettro.setId(3L);
        jettro.setName("Jettro");
        Participant marijn = new Participant();
        marijn.setId(3L);
        marijn.setName("Marijn");
        foundRaffle.addParticipant(jettro);
        foundRaffle.addParticipant(marijn);
        return foundPrice;
    }

    public void testChooseWinnerWhileAlreadyExists() {
        Price inputPrize = new Price();
        inputPrize.setId(3L);
        Price foundPrize = createFoundPrize(inputPrize);
        Winner winner = new Winner(foundPrize, foundPrize.getRaffle().getParticipants().get(0));
        foundPrize.setWinner(winner);

        expect(mockPriceDao.loadById(inputPrize.getId())).andReturn(foundPrize);

        replay(mockPriceDao, mockRandomizer, mockRaffleDao);

        try {
            raffleService.chooseWinnerForPrice(inputPrize);
            fail("A WinnerHasBeenSelectedException should have been thrown");
        } catch (WinnerHasBeenSelectedException e) {
            // as expected
        }

        verify(mockPriceDao, mockRandomizer, mockRaffleDao);
    }

    public void testGiveRandomParticipants() {
        Raffle raffle = new Raffle();
        Long raffleId = 99L;
        raffle.setId(raffleId);
        Participant jettro = new Participant();
        jettro.setId(3L);
        jettro.setName("Jettro");
        Participant marijn = new Participant();
        marijn.setId(3L);
        marijn.setName("Marijn");
        raffle.addParticipant(jettro);
        raffle.addParticipant(marijn);
        Integer numParticipants = 2;

        expect(mockRaffleDao.loadById(raffleId)).andReturn(raffle);
        expect(mockRandomizer.createRandomNumber(raffle.getParticipants().size())).andReturn(1);
        expect(mockRandomizer.createRandomNumber(raffle.getParticipants().size())).andReturn(1);

        replay(mockPriceDao, mockRandomizer, mockRaffleDao);

        List<Participant> participants = raffleService.giveRandomParticipants(raffle, numParticipants);

        verify(mockPriceDao, mockRandomizer, mockRaffleDao);
        assertNotNull("Returned participants cannot be null", participants);
        assertEquals("Number of returned participants is not correct", numParticipants.intValue(), participants.size());
    }

    public void testReturnRaffleById() {
        Raffle raffle = new Raffle();
        raffle.setId(3L);

        expect(mockRaffleDao.loadById(raffle.getId())).andReturn(raffle);

        replay(mockPriceDao, mockRandomizer, mockRaffleDao);

        Raffle foundRaffle = raffleService.giveRaffleById(raffle.getId());

        verify(mockPriceDao, mockRandomizer, mockRaffleDao);

        assertNotNull("raffle should not be null", foundRaffle);
    }

    public void testStoreRaffle() {
        Raffle raffle = new Raffle();
        raffle.setId(3L);

        expect(mockRaffleDao.save(raffle)).andReturn(raffle);
        replay(mockPriceDao, mockRandomizer, mockRaffleDao);

        raffleService.storeRaffle(raffle);

        verify(mockPriceDao, mockRandomizer, mockRaffleDao);
    }

    public void testDeleteRaffle() {
        Raffle raffle = new Raffle();
        raffle.setId(3L);

        mockRaffleDao.delete(raffle);
        expectLastCall().once();
        replay(mockPriceDao, mockRandomizer, mockRaffleDao);

        raffleService.removeRaffle(raffle);

        verify(mockPriceDao, mockRandomizer, mockRaffleDao);
    }

    public void testDeleteParticipantFromRaffle() {
        Participant participant = new Participant();
        participant.setId(7L);
        Raffle raffle = new Raffle();
        raffle.setId(5L);
        raffle.addParticipant(participant);

        expect(mockRaffleDao.loadById(raffle.getId())).andReturn(raffle);
        expect(mockParticipantDao.loadById(participant.getId())).andReturn(participant);
        mockParticipantDao.delete(participant);
        expectLastCall().once();
        replay(mockPriceDao, mockRandomizer, mockRaffleDao, mockParticipantDao);

        raffleService.removeParticipantFromRaffle(participant);
        verify(mockPriceDao, mockRandomizer, mockRaffleDao, mockParticipantDao);
    }

    public void testDeleteParticipantExistingWinnerFromRaffle() {
        Participant participant = new Participant();
        participant.setId(7L);
        Raffle raffle = new Raffle();
        raffle.setId(5L);
        raffle.addParticipant(participant);
        Price price = new Price();
        price.setId(3L);
        Winner winner = new Winner(price, participant);
        price.setWinner(winner);
        raffle.addPrice(price);
        expect(mockRaffleDao.loadById(raffle.getId())).andReturn(raffle);
        expect(mockParticipantDao.loadById(participant.getId())).andReturn(participant);
        replay(mockPriceDao, mockRandomizer, mockRaffleDao, mockParticipantDao);

        try {
            raffleService.removeParticipantFromRaffle(participant);
            fail("A participantIsAWinnerException should have been thrown");
        } catch (ParticipantIsAWinnerException e) {
            // as expected
        }

        verify(mockPriceDao, mockRandomizer, mockRaffleDao, mockParticipantDao);
    }

    public void testDeletePrizeFromRaffle() {
        Price price = new Price();
        price.setId(3L);
        Raffle raffle = new Raffle();
        raffle.setId(5L);
        raffle.addPrice(price);

        expect(mockRaffleDao.loadById(raffle.getId())).andReturn(raffle);
        expect(mockPriceDao.loadById(price.getId())).andReturn(price);
        mockPriceDao.delete(price);
        expectLastCall().once();
        replay(mockPriceDao, mockRandomizer, mockRaffleDao);

        raffleService.removePriceFromRaffle(price);
        verify(mockPriceDao, mockRandomizer, mockRaffleDao);
    }

}
