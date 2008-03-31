package nl.gridshore.samples.training.business.impl;

import nl.gridshore.samples.training.business.TrainingService;
import nl.gridshore.samples.training.domain.Training;
import nl.gridshore.samples.training.domain.TrainingSession;
import nl.gridshore.samples.training.domain.TrainingPlanning;
import nl.gridshore.samples.training.dataaccess.TrainingDao;
import nl.gridshore.samples.training.dataaccess.TrainingSessionDao;
import nl.gridshore.samples.training.dataaccess.TrainingPlanningDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 24, 2008
 * Time: 1:46:09 PM
 * Implementation for the training interface
 */
public class TrainingServiceImpl implements TrainingService {
    private TrainingDao trainingDao;
    private TrainingSessionDao trainingSessionDao;
    private TrainingPlanningDao trainingPlanningDao;

    @Autowired
    public TrainingServiceImpl(TrainingDao trainingDao, TrainingSessionDao trainingSessionDao,
                               TrainingPlanningDao trainingPlanningDao) {
        this.trainingDao = trainingDao;
        this.trainingSessionDao = trainingSessionDao;
        this.trainingPlanningDao = trainingPlanningDao;
    }

    public List<Training> obtainAllTrainings() {
        return trainingDao.loadAll();
    }

    public Training obtainTrainingById(Long trainingId) {
        return trainingDao.loadById(trainingId);
    }

    public void storeTraining(Training training) {
        trainingDao.save(training);
    }

    public TrainingSession obtainTrainingSessionById(Long trainingsessionId) {
        return trainingSessionDao.loadById(trainingsessionId);
    }

    public List<TrainingPlanning> obtainPlannedAttendenceTrainingSession(Long trainingsessionId) {
        return trainingPlanningDao.findByTrainingSession(trainingsessionId);
    }
}
