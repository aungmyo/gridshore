package nl.gridshore.samples.training.web;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.springframework.ui.ModelMap;
import nl.gridshore.samples.training.web.controllers.training.TrainingController;
import nl.gridshore.samples.training.business.TrainingService;
import nl.gridshore.samples.training.domain.Training;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 26, 2008
 * Time: 1:54:56 PM
 * Test class for the TrainingController
 */
@RunWith (JUnit4ClassRunner.class)
public class TrainingControllerTest {
    TrainingService mockTrainingService;

    TrainingController trainingController;

    @Before
    public void initializeTest() {
        mockTrainingService = createMock(TrainingService.class);
        trainingController = new TrainingController(mockTrainingService);
    }

    @Test
    public void trainingsHandlerReturnsView() {
        List<Training> trainings = new ArrayList<Training>();
        expect(mockTrainingService.obtainAllTrainings()).andReturn(trainings);
        replay(mockTrainingService);

        ModelMap modelMap = trainingController.trainingsHandler();

        verify(mockTrainingService);

        assertTrue("The list of trainings is not stored as expected",modelMap.containsAttribute("trainingsList"));
    }
}
