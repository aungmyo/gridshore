package nl.gridshore.samples.training.dataaccess.jpa;

import nl.gridshore.samples.training.domain.Training;
import nl.gridshore.samples.training.dataaccess.TrainingDao;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 5:07:01 PM
 * Data access class for training objects using the base dao jpa implementation.
 */
public class TrainingDaoJpa extends BaseDaoJpa<Training> implements TrainingDao {
    public TrainingDaoJpa() {
        super(Training.class, "Training");
    }
}
