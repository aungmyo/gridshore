package nl.gridshore.samples.training.dataaccess.jpa;

import nl.gridshore.samples.training.dataaccess.TrainingPlanningDao;
import nl.gridshore.samples.training.dataaccess.BaseDao;
import nl.gridshore.samples.training.domain.TrainingPlanning;
import nl.gridshore.samples.training.domain.TrainingSession;

import javax.persistence.Query;
import java.util.List;

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


    public List<TrainingPlanning> findByTrainingSession(Long trainingSessionId) {
        String queryStr = "select tp from TrainingPlanning tp where tp.session.id = :session";
        Query query = getEntityManager().createQuery(queryStr);
        query.setParameter("session",trainingSessionId);
        //noinspection unchecked
        return query.getResultList();
    }
    
}
