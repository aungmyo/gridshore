package nl.gridshore.samples.training.domain;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 11:59:57 PM
 * Enumertion type for the status of a session
 */
public enum SessionStatus {
    SCHEDULED("scheduled"),
    CANCELLED("cancelled"),
    FINISHED("finished");

    private SessionStatus (String code) {
        this.code = code;
    }

    public String toString() {
        return code;
    }

    private final String code;
}
