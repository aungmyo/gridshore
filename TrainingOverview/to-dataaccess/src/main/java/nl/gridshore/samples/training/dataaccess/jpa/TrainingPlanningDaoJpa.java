package nl.gridshore.samples.training.dataaccess.jpa;

import nl.gridshore.samples.training.dataaccess.TrainingPlanningDao;
import nl.gridshore.samples.training.dataaccess.BaseDao;
import nl.gridshore.samples.training.domain.TrainingPlanning;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 1, 2008
 * Time: 9:04:52 PM
 * Default Jpa implementation for data access compnent relating to Training planning objects
 */
public class TrainingPlanningDaoJpa extends BaseDaoJpa<TrainingPlanning> implements TrainingPlanningDao {
    public TrainingPlanningDaoJpa() {
        super(TrainingPlanning.class,"TrainingPlanning");
    }
}
