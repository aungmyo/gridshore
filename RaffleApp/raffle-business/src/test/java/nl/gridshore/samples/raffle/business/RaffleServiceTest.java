package nl.gridshore.samples.raffle.business;

import nl.gridshore.samples.raffle.business.exceptions.ParticipantIsAWinnerException;
import nl.gridshore.samples.raffle.business.exceptions.PrizeDoesNotHaveAWinnerException;
import nl.gridshore.samples.raffle.business.exceptions.UnknownRaffleException;
import nl.gridshore.samples.raffle.business.exceptions.WinnerHasBeenSelectedException;
import nl.gridshore.samples.raffle.business.impl.RaffleServiceImpl;
import nl.gridshore.samples.raffle.dao.ParticipantDao;
import nl.gridshore.samples.raffle.dao.PrizeDao;
import nl.gridshore.samples.raffle.dao.RaffleDao;
import nl.gridshore.samples.raffle.dao.WinnerDao;
import nl.gridshore.samples.raffle.dao.exceptions.EntityNotFoundException;
import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Prize;
import nl.gridshore.samples.raffle.domain.Raffle;
import nl.gridshore.samples.raffle.domain.Winner;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 18, 2007
 * Time: 10:20:51 PM
 * test case for testing the business service RaffleService using mock objects for dataaccess components
 */
public class RaffleServiceTest {
    private RaffleDao mockRaffleDao;
    private PrizeDao mockPrizeDao;
    private Randomizer mockRandomizer;
    private ParticipantDao mockParticipantDao;
    private WinnerDao mockWinnerDao;

    private RaffleService raffleService;

    @Before
    public void createMocksAndService() {
        mockRaffleDao = createMock(RaffleDao.class);
        mockParticipantDao = createMock(ParticipantDao.class);
        mockPrizeDao = createMock(PrizeDao.class);
        mockRandomizer = createMock(Randomizer.class);
        mockWinnerDao = createMock(WinnerDao.class);
        raffleService = new RaffleServiceImpl(mockRaffleDao, mockParticipantDao, mockPrizeDao, mockWinnerDao, mockRandomizer);
    }

    @Test
    public void giveAllRaffles() {
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

    @Test
    public void chooseWinnerForPrize() {
        Prize inputPrize = new Prize();
        inputPrize.setId(3L);
        Prize foundPrize = createFoundPrize(inputPrize);

        expect(mockPrizeDao.loadById(inputPrize.getId())).andReturn(foundPrize);
        expect(mockRandomizer.createRandomNumber(foundPrize.getRaffle().getParticipants().size())).andReturn(1);

        replay(mockPrizeDao, mockRandomizer, mockRaffleDao);

        Prize prizeForWinner = raffleService.chooseWinnerForPrize(inputPrize);

        verify(mockPrizeDao, mockRandomizer, mockRaffleDao);
        assertNotNull("The returned prize is null", prizeForWinner);
        assertNotNull("winner is not stored, the prize did not contain a winner", prizeForWinner.getWinner());
    }

    private Prize createFoundPrize(Prize inputPrize) {
        Prize foundPrize = new Prize();
        foundPrize.setId(inputPrize.getId());
        foundPrize.setDescription("Prize for testing");
        Raffle foundRaffle = new Raffle();
        foundRaffle.setId(1L);
        foundRaffle.setDescription("Raffle for testing");
        foundRaffle.addPrize(foundPrize);
        Participant jettro = new Participant();
        jettro.setId(3L);
        jettro.setName("Jettro");
        Participant marijn = new Participant();
        marijn.setId(3L);
        marijn.setName("Marijn");
        foundRaffle.addParticipant(jettro);
        foundRaffle.addParticipant(marijn);
        return foundPrize;
    }

    @Test(expected = WinnerHasBeenSelectedException.class)
    public void chooseWinnerForPriceWhileWinnerAlreadyExists() {
        Prize inputPrize = new Prize();
        inputPrize.setId(3L);
        Prize foundPrize = createFoundPrize(inputPrize);
        Winner winner = new Winner(foundPrize, foundPrize.getRaffle().getParticipants().get(0));
        foundPrize.setWinner(winner);

        expect(mockPrizeDao.loadById(inputPrize.getId())).andReturn(foundPrize);

        replay(mockPrizeDao, mockRandomizer, mockRaffleDao);

        raffleService.chooseWinnerForPrize(inputPrize);

        verify(mockPrizeDao, mockRandomizer, mockRaffleDao);
    }

    @Test
    public void giveRandomParticipants() {
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

        replay(mockPrizeDao, mockRandomizer, mockRaffleDao);

        List<Participant> participants = raffleService.giveRandomParticipants(raffle, numParticipants);

        verify(mockPrizeDao, mockRandomizer, mockRaffleDao);
        assertNotNull("Returned participants cannot be null", participants);
        assertEquals("Number of returned participants is not correct", numParticipants.intValue(), participants.size());
    }

    @Test
    public void testReturnRaffleById() {
        Raffle raffle = new Raffle();
        raffle.setId(3L);

        expect(mockRaffleDao.loadById(raffle.getId())).andReturn(raffle);

        replay(mockPrizeDao, mockRandomizer, mockRaffleDao);

        Raffle foundRaffle = raffleService.giveRaffleById(raffle.getId());

        verify(mockPrizeDao, mockRandomizer, mockRaffleDao);

        assertNotNull("raffle should not be null", foundRaffle);
    }

    @Test(expected = UnknownRaffleException.class)
    public void testReturnRaffleByNonExsitingId() {
        Raffle raffle = new Raffle();
        raffle.setId(999L);
        expect(mockRaffleDao.loadById(raffle.getId())).andThrow(new EntityNotFoundException(Raffle.class, raffle.getId()));
        replay(mockPrizeDao, mockRandomizer, mockRaffleDao);

        raffleService.giveRaffleById(raffle.getId());

        verify(mockPrizeDao, mockRandomizer, mockRaffleDao);
    }

    @Test
    public void storeRaffle() {
        Raffle raffle = new Raffle();
        raffle.setId(3L);

        expect(mockRaffleDao.save(raffle)).andReturn(raffle);
        replay(mockPrizeDao, mockRandomizer, mockRaffleDao);

        raffleService.storeRaffle(raffle);

        verify(mockPrizeDao, mockRandomizer, mockRaffleDao);
    }

    @Test
    public void deleteRaffle() {
        Raffle raffle = new Raffle();
        raffle.setId(3L);

        mockRaffleDao.delete(raffle);
        expectLastCall().once();
        replay(mockPrizeDao, mockRandomizer, mockRaffleDao);

        raffleService.removeRaffle(raffle);

        verify(mockPrizeDao, mockRandomizer, mockRaffleDao);
    }

    @Test
    public void deleteParticipantFromRaffle() {
        Participant participant = new Participant();
        participant.setId(7L);
        Raffle raffle = new Raffle();
        raffle.setId(5L);
        raffle.addParticipant(participant);

        expect(mockRaffleDao.loadById(raffle.getId())).andReturn(raffle);
        expect(mockParticipantDao.loadById(participant.getId())).andReturn(participant);
        mockParticipantDao.delete(participant);
        expectLastCall().once();
        replay(mockPrizeDao, mockRandomizer, mockRaffleDao, mockParticipantDao);

        raffleService.removeParticipantFromRaffle(participant);
        verify(mockPrizeDao, mockRandomizer, mockRaffleDao, mockParticipantDao);
    }

    @Test(expected = ParticipantIsAWinnerException.class)
    public void testDeleteParticipantExistingWinnerFromRaffle() {
        Participant participant = new Participant();
        participant.setId(7L);
        Raffle raffle = new Raffle();
        raffle.setId(5L);
        raffle.addParticipant(participant);
        Prize prize = new Prize();
        prize.setId(3L);
        Winner winner = new Winner(prize, participant);
        prize.setWinner(winner);
        raffle.addPrize(prize);
        expect(mockRaffleDao.loadById(raffle.getId())).andReturn(raffle);
        expect(mockParticipantDao.loadById(participant.getId())).andReturn(participant);
        replay(mockPrizeDao, mockRandomizer, mockRaffleDao, mockParticipantDao);

        raffleService.removeParticipantFromRaffle(participant);

        verify(mockPrizeDao, mockRandomizer, mockRaffleDao, mockParticipantDao);
    }

    @Test
    public void deletePrizeFromRaffle() {
        Prize prize = new Prize();
        prize.setId(3L);
        Raffle raffle = new Raffle();
        raffle.setId(5L);
        raffle.addPrize(prize);

        expect(mockRaffleDao.loadById(raffle.getId())).andReturn(raffle);
        expect(mockPrizeDao.loadById(prize.getId())).andReturn(prize);
        mockPrizeDao.delete(prize);
        expectLastCall().once();
        replay(mockPrizeDao, mockRandomizer, mockRaffleDao);

        raffleService.removePrizeFromRaffle(prize);
        verify(mockPrizeDao, mockRandomizer, mockRaffleDao);
    }

    @Test
    public void deleteWinnerFromPrize() {
        Prize prize = new Prize();
        prize.setId(3L);
        Participant participant = new Participant();
        Winner winner = new Winner(prize, participant);
        prize.setWinner(winner);
        expect(mockPrizeDao.loadById(prize.getId())).andReturn(prize);
        replay(mockPrizeDao, mockRandomizer, mockRaffleDao);

        raffleService.removeWinnerFromPrize(prize);
        verify(mockPrizeDao, mockRandomizer, mockRaffleDao);
    }

    @Test(expected = PrizeDoesNotHaveAWinnerException.class)
    public void deleteNonExistingWinnerFromPrize() {
        Prize prize = new Prize();
        prize.setId(3L);
        expect(mockPrizeDao.loadById(prize.getId())).andReturn(prize);
        replay(mockPrizeDao, mockRandomizer, mockRaffleDao);

        raffleService.removeWinnerFromPrize(prize);

        verify(mockPrizeDao, mockRandomizer, mockRaffleDao);
    }

}
