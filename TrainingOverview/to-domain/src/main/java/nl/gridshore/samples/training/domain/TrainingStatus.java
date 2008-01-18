package nl.gridshore.samples.training.domain;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 11:41:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class TrainingStatus extends BaseDomain {
    private Employee employee;
    private Training training;
    private EmployeeTrainingRelation currentRelation;

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

    public EmployeeTrainingRelation getCurrentRelation() {
        return currentRelation;
    }

    public void setCurrentRelation(EmployeeTrainingRelation currentRelation) {
        this.currentRelation = currentRelation;
    }
}
