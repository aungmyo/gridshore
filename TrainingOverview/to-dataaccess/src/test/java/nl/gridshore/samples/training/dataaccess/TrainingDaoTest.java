package nl.gridshore.samples.training.dataaccess;

import org.springframework.test.jpa.AbstractJpaTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.junit.runner.RunWith;
import org.junit.Test;
import nl.gridshore.samples.training.domain.Training;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 9:23:34 AM
 * Unit test class for testing the TrainingDao class
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-dataaccess-config.xml","classpath:dataaccess-config.xml"})
public class TrainingDaoTest extends AbstractJpaTests {
    private TrainingDao trainingDao;

    @Autowired
    public void setTrainingDao(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    @Test
    @Transactional
    public void createTraining() {
        int numTrainings = trainingDao.loadAll().size();

        Training training = new Training();
        training.setCode("AA1299");
        training.setName("Test training");
        training.setRemark("Nothing special");

        trainingDao.save(training);

        assertEquals("Number of training should have been increased with one", numTrainings + 1, trainingDao.loadAll().size());
    }
}
