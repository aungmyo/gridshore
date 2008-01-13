package nl.gridshore.samples.raffle.web.springmvc;

import nl.gridshore.samples.raffle.business.RaffleService;
import nl.gridshore.samples.raffle.domain.Participant;
import nl.gridshore.samples.raffle.domain.Raffle;
import nl.gridshore.samples.raffle.web.springmvc.validator.ParticipantValidator;
import static org.easymock.EasyMock.*;
import org.easymock.classextension.EasyMock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 13, 2008
 * Time: 8:02:34 PM
 * Test class fo the AddParticipantController
 */
public class AddParticipantControllerTest {
    private AddParticipantController controller;
    private RaffleService mockRaffleService;
    private BindingResult mockBindingResult;
    private SessionStatus mockSessionStatus;
    private ParticipantValidator mockValidator;

    @Before
    public void initilizeController() {
        mockRaffleService = createMock(RaffleService.class);
        mockBindingResult = createMock(BindingResult.class);
        mockSessionStatus = createMock(SessionStatus.class);
        mockValidator = EasyMock.createMock(ParticipantValidator.class);

        controller = new AddParticipantController(mockRaffleService, mockValidator);
    }

    @Test
    public void setupForm() {
        Raffle foundRaffle = new Raffle();
        foundRaffle.setId(5L);
        ModelMap paramsMap = new ModelMap();
        expect(mockRaffleService.giveRaffleById(foundRaffle.getId())).andReturn(foundRaffle);
        replay(mockRaffleService);

        String view = controller.setupForm(5, paramsMap);

        verify(mockRaffleService);

        assertEquals("returned view is not as expected", "participantForm", view);
        assertNotNull("A participant should have been added to the modelmap", paramsMap.get("participant"));
    }

    @Test
    public void processSubmit() {
        Participant participant = new Participant();
        participant.setName("Jettro Coenradie");
        participant.setRaffle(new Raffle());
        participant.getRaffle().setId(5L);

        mockValidator.validate(participant, mockBindingResult);
        EasyMock.expectLastCall().once();
        expect(mockBindingResult.hasErrors()).andReturn(false);
        mockRaffleService.storeRaffle(participant.getRaffle());
        expectLastCall().once();
        mockSessionStatus.setComplete();
        expectLastCall().once();
        replay(mockBindingResult, mockRaffleService, mockSessionStatus);
        EasyMock.replay(mockValidator);

        String view = controller.processSubmit(participant, mockBindingResult, mockSessionStatus);

        verify(mockBindingResult, mockRaffleService, mockSessionStatus);
        EasyMock.verify(mockValidator);
        assertEquals("return view is not as expected",
                "redirect:editraffle.view?raffleId=" + participant.getRaffle().getId(), view);
    }

    @Test
    public void processSubmitBindingErrors() {
        Participant participant = new Participant();
        mockValidator.validate(participant, mockBindingResult);
        EasyMock.expectLastCall().once();
        expect(mockBindingResult.hasErrors()).andReturn(true);
        replay(mockBindingResult, mockRaffleService, mockSessionStatus);
        EasyMock.replay(mockValidator);

        String view = controller.processSubmit(participant, mockBindingResult, mockSessionStatus);

        verify(mockBindingResult, mockRaffleService, mockSessionStatus);
        EasyMock.verify(mockValidator);
        assertEquals("returned view is not as expected", "participantForm", view);
    }
}
