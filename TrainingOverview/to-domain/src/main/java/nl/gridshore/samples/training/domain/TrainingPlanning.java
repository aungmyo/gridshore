package nl.gridshore.samples.training.domain;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 11:36:59 PM
 * Represents the planning for an Employee to follow a sessions when available.
 */
public class TrainingPlanning extends BaseDomain {
    private Employee employee;
    private TrainingSession session;
    private SessionStatus status;

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

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }
}
