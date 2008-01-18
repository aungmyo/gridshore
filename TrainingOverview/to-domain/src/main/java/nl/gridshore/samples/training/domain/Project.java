package nl.gridshore.samples.training.domain;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 11:36:30 PM
 * Representation of the project our employees can work on. Contains information about the project manager which is
 * needed for communication.
 */
public class Project extends BaseDomain {
    private String name;
    private String client;
    private String managerName;
    private String managerEmail;
    private String wbs;

    private List<Employee> employees = new ArrayList<Employee>();
}
