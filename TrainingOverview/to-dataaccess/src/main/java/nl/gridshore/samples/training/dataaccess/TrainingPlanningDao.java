package nl.gridshore.samples.training.dataaccess;

import nl.gridshore.samples.training.domain.TrainingPlanning;
import nl.gridshore.samples.training.domain.TrainingSession;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 1, 2008
 * Time: 9:03:41 PM
 * Data access class for training planning objects
 */
public interface TrainingPlanningDao extends BaseDao<TrainingPlanning> {
    List<TrainingPlanning> findByTrainingSession(Long  trainingSessionId);
    
}
