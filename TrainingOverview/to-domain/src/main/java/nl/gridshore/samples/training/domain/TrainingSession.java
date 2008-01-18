package nl.gridshore.samples.training.domain;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 11:37:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class TrainingSession extends BaseDomain {
    private Integer weekNr;
    private Training training;
    private TrainingStatus status;

    public Integer getWeekNr() {
        return weekNr;
    }

    public void setWeekNr(Integer weekNr) {
        this.weekNr = weekNr;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public TrainingStatus getStatus() {
        return status;
    }

    public void setStatus(TrainingStatus status) {
        this.status = status;
    }
}
