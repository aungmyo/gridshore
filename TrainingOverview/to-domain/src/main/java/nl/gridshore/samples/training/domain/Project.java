package nl.gridshore.samples.training.domain;

import javax.persistence.*;
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
@Entity
@Table (name = "to_project")
public class Project extends BaseDomain {
    private String name;
    private String client;
    private String managerName;
    private String managerEmail;
    private String wbs;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private List<Employee> employees = new ArrayList<Employee>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getWbs() {
        return wbs;
    }

    public void setWbs(String wbs) {
        this.wbs = wbs;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        employee.setProject(this);
        this.employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employee.setProject(null);
        this.employees.remove(employee);
    }
}
