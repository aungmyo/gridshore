package nl.gridshore.samples.training.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 11:36:59 PM
 * Represents the planning for an Employee to follow a sessions when available.
 */
@Entity
@Table (name = "to_trainingplanning")
public class TrainingPlanning extends BaseDomain {
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "trainingsession_id")
    private TrainingSession session;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TrainingSession getSession() {
        return session;
    }

    public void setSession(TrainingSession session) {
        this.session = session;
    }

}
