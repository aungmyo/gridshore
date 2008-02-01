package nl.gridshore.samples.training.domain;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.MinLength;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 11:37:18 PM
 * Domain class representing a training object
 */
@Entity
@Table (name = "to_training")
public class Training extends BaseDomain {
    @NotNull
    @Length(min = 3, max = 50 )
    private String name;
    @MinLength(value = 5)
    private String code;
    @NotBlank
    private String remark;

    @OneToMany(mappedBy = "training", fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.REMOVE})
    private List<TrainingSession> sessions = new ArrayList<TrainingSession>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<TrainingSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<TrainingSession> sessions) {
        this.sessions = sessions;
    }

    public void addTrainingSession(TrainingSession trainingSession) {
        trainingSession.setTraining(this);
        this.sessions.add(trainingSession);
    }

    public void removeTrainingSession(TrainingSession trainingSession) {
        trainingSession.setTraining(null);
        this.sessions.remove(trainingSession);
    }
}
