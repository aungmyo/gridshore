package nl.gridshore.samples.training.domain;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 3, 2008
 * Time: 9:49:34 AM
 * Enmeration to describe the relation between an employee and a certain training.
 */
public enum EmployeeTrainingInterest {
    INTERESTED("interested"),
    SCHEDULED("scheduled"),
    DONE("done");

    private EmployeeTrainingInterest(String code) {
        this.code = code;
    }

    public final String toString() {
        return this.code;
    }

    private final String code;
}
