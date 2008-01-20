package nl.gridshore.samples.training.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 11:37:09 PM
 * Entity object representing an instance of a session of a specific training
 */
@Entity
@Table (name = "to_trainingsession")
public class TrainingSession extends BaseDomain {
    private Integer weekNr;
    @ManyToOne
    @JoinColumn(name = "training_id")        
    private Training training;
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
