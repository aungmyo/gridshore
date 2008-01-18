package nl.gridshore.samples.training.domain;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 11:43:26 PM
 * To change this template use File | Settings | File Templates.
 */
public enum EmployeeTrainingRelation {
    INTERESTED("interested"),
    FOLLOWED("followed"),
    PLANNED("planned"),
    CANCELLEDPROJECT("cancelledproject"),
    CANCELLEDOTHER("cancelledother");

    private EmployeeTrainingRelation(String code) {
        this.code = code;
    }

    public String toString() {
        return this.code;
    }

    private final String code;
}
