package nl.gridshore.samples.training.business;

import nl.gridshore.samples.training.domain.Training;
import nl.gridshore.samples.training.domain.TrainingSession;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 24, 2008
 * Time: 1:26:08 PM
 * Service containing all training related business services
 */
public interface TrainingService {
    /**
     * Returns a list with all trainings
     * @return List of trainings
     */
    List<Training> obtainAllTrainings();

    /**
     * returns the training belonging to the provided id
     * @param trainingId Long representing the id of the training to look for
     * @return Training found belonging to the id
     */
    Training obtainTrainingById(Long trainingId);

    /**
     * Store the provided training in the repository
     * @param training Training to store
     */
    void storeTraining(Training training);

    /**
     * Returns the training session belonging to the provided id
     * @param trainingsessionId Long representing the id of the training sessions to load
     * @return TrainingSession belonging to the provided id
     */
    TrainingSession obtainTrainingSessionById(Long trainingsessionId);
}
