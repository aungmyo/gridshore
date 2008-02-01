package nl.gridshore.samples.training.dataaccess.jpa;

import nl.gridshore.samples.training.dataaccess.TrainingSessionDao;
import nl.gridshore.samples.training.domain.TrainingSession;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 29, 2008
 * Time: 10:24:23 PM
 * Data access implementation using jpa for TrainingSessions
 */
public class TrainingSessionDaoJpa extends BaseDaoJpa<TrainingSession> implements TrainingSessionDao  {
    public TrainingSessionDaoJpa() {
        super(TrainingSession.class, "TrainingSession");
    }
}
