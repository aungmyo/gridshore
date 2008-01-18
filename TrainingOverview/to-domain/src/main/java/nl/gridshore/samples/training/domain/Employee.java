package nl.gridshore.samples.training.domain;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 11:35:37 PM
 * The employee is most important in this application, we need to check his wishes, his planning and his progress
 */
public class Employee extends BaseDomain {
    private String employeeNumber;
    private String longName;
    private String cell;
    private String cluster;
    private String level;

    private Project project;
    private List<TrainingPlanning> trainingPlans = new ArrayList<TrainingPlanning>();
    private List<TrainingStatus> trainingWishes = new ArrayList<TrainingStatus>();

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<TrainingPlanning> getTrainingPlans() {
        return trainingPlans;
    }

    public void addTrainingPlanning(TrainingPlanning trainingPlanning) {
        trainingPlanning.setEmployee(this);
        this.trainingPlans.add(trainingPlanning);
    }

    public void removeTrainingPlanning(TrainingPlanning trainingPlanning) {
        this.trainingPlans.remove(trainingPlanning);
    }

    public void setTrainingPlans(List<TrainingPlanning> trainingPlans) {
        this.trainingPlans = trainingPlans;
    }

    public List<TrainingStatus> getTrainingWishes() {
        return trainingWishes;
    }

    public void setTrainingWishes(List<TrainingStatus> trainingWishes) {
        this.trainingWishes = trainingWishes;
    }

    public void addTraningsWishes(TrainingStatus trainingStatus) {
        trainingStatus.setEmployee(this);
        this.trainingWishes.add(trainingStatus);
    }

    public void removeTraningsWishes(TrainingStatus trainingStatus) {
        this.trainingWishes.remove(trainingStatus);
    }
}
