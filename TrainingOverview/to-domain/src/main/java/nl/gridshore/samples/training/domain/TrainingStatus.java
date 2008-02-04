package nl.gridshore.samples.training.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 11:41:05 PM
 * Entity object representing the relation between a trainin and an employee
 */
@Entity
@Table (name = "to_trainingstatus")
public class TrainingStatus extends BaseDomain {
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;
    private EmployeeTrainingInterest currentRelation;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public EmployeeTrainingInterest getCurrentRelation() {
        return currentRelation;
    }

    public void setCurrentRelation(EmployeeTrainingInterest currentRelation) {
        this.currentRelation = currentRelation;
    }
}
