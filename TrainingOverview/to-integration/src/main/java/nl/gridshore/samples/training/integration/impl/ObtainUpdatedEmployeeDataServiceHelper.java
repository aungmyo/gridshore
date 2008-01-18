package nl.gridshore.samples.training.integration.impl;

import nl.gridshore.samples.training.integration.vo.UpdatedEmployeeData;

import java.util.Set;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 18, 2008
 * Time: 9:43:28 PM
 * Helper class to keep track of the loaded employees and to provide some utility functions. Based on state, this
 * cannot be a singleton.
 */
class ObtainUpdatedEmployeeDataServiceHelper  {
    private Set<SubEmployee> employees = new HashSet<SubEmployee>();

    public Set<UpdatedEmployeeData> getAllEmployees() {
        this.cleanEmployees();
        Set<UpdatedEmployeeData> foundEmployees = new HashSet<UpdatedEmployeeData>();
        for(SubEmployee subEmp : employees) {
            foundEmployees.add((UpdatedEmployeeData) subEmp);
        }
        return foundEmployees;
    }

    public void addUpdatedEmployee(String key) {
        try {
            getUpdatedEmployee(key);
        } catch (RuntimeException e) {
            SubEmployee emp = new SubEmployee();
            emp.setKey(key);
            employees.add(emp);
        }
    }

    public UpdatedEmployeeData getUpdatedEmployee(String key) {
        for(SubEmployee emp : employees) {
            if (emp.getKey().equals(key)) {
                return emp;
            }
        }
        throw new RuntimeException("key not found : " + key);
    }


    public void cleanEmployees() {
        Set<SubEmployee> toBeDeletedEmps = new HashSet<SubEmployee>();
        for(SubEmployee emp : employees) {
            if (null == emp.getIdNumber() || "".equals(emp.getIdNumber())) {
                toBeDeletedEmps.add(emp);
            }
        }
        employees.removeAll(toBeDeletedEmps);
    }

    private class SubEmployee extends UpdatedEmployeeData {
        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

}
