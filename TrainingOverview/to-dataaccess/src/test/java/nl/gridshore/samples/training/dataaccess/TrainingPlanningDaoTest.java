package nl.gridshore.samples.training.dataaccess;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.jpa.AbstractJpaTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import nl.gridshore.samples.training.domain.TrainingSession;
import nl.gridshore.samples.training.domain.TrainingPlanning;
import nl.gridshore.samples.training.dataaccess.testhelp.DatabaseOperations;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-dataaccess-config.xml","classpath:dataaccess-config.xml"})
public class TrainingPlanningDaoTest extends AbstractJpaTests {
    private TrainingPlanningDao trainingPlanningDao;

    @Autowired
    public void setTrainingPlanningDao(TrainingPlanningDao trainingPlanningDao) {
        this.trainingPlanningDao = trainingPlanningDao;
    }

    @Test
    @Transactional (readOnly = true)
    public void obtainTrainingPlanningsForSession() {
        List<TrainingPlanning> plannings = trainingPlanningDao.findByTrainingSession(
                (long)DatabaseOperations.TRAININGSESSION_SCHEDULED);
        assertNotNull("returned collection containing training plannings should not be null",plannings);
        assertEquals("number of returned trainingplannings is not right",1,plannings.size());
        assertEquals("Id of returned employee that has panned a session is not right",
                DatabaseOperations.EMPLOYEE_WITH_PROJECT,plannings.get(0).getEmployee().getId().intValue());
    }
}
