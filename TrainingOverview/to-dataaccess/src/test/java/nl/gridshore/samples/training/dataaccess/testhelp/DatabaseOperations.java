package nl.gridshore.samples.training.dataaccess.testhelp;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.dao.DataAccessException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import nl.gridshore.samples.training.domain.EmployeeTrainingRelation;
import nl.gridshore.samples.training.domain.SessionStatus;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 20, 2008
 * Time: 9:18:32 AM
 * Helper class used for testing to initilize the database.
 */
public class DatabaseOperations extends JdbcDaoSupport {
    public static final int PROJECT_COMPLETE_ID = 1;
    public static final int PROJECT_NO_EMPLOYEES_ID = 2;
    public static final int EMPLOYEE_WITH_PROJECT = 9;
    public static final int EMPLOYEE_NO_PROJECT = 10;
    public static final int TRAINING_WITH_SESSIONS = 21;
    public static final int TRAINING_NO_SESSIONS = 22;
    public static final int TRAININGSESSION_SCHEDULED = 201;


    private static int numRuns = 0;

    public DatabaseOperations() {
        System.out.println("***************************** " + ++numRuns  + "Database Operations object created");
    }

    public void initializeDatabase() {
        createProjects();
        createTrainings();
        createEmployees();
        createTrainingStatussus();
        createTrainingSessions();
        createTrainingPlannings();
    }

    private void createTrainingSessions() {
        createTrainingSession(TRAININGSESSION_SCHEDULED,SessionStatus.SCHEDULED,48,TRAINING_WITH_SESSIONS);
    }


    private void createTrainingPlannings() {
        createTrainingPlanning(9,EMPLOYEE_WITH_PROJECT,TRAININGSESSION_SCHEDULED);
    }

    private void createTrainingStatussus() {
        createTrainingStatus(101, EmployeeTrainingRelation.INTERESTED,EMPLOYEE_NO_PROJECT,TRAINING_WITH_SESSIONS);
        createTrainingStatus(102, EmployeeTrainingRelation.CANCELLEDPROJECT,EMPLOYEE_WITH_PROJECT,TRAINING_WITH_SESSIONS);
    }

    private void createTrainings() {
        createTraining(TRAINING_WITH_SESSIONS,"KZ12345","Plannend training","This training is planned");
        createTraining(TRAINING_NO_SESSIONS,"AA1234","No sessions", "There are no sessions planned for this training");
    }

    private void createProjects() {
        createProject(PROJECT_COMPLETE_ID, "Abnamro", "d.bruno@accenture.com", "Daniele Bruno", "OCS", "BL1234");
        createProject(PROJECT_NO_EMPLOYEES_ID, "KLM", "a.van.mierlo@accenture.com", "Arjen van Mierlo", "RPSL", "BL4567");
    }

    private void createEmployees() {
        createEmployee(EMPLOYEE_WITH_PROJECT, "Java/Web", "T", "12345678","1. programmer", "Jan Programer", PROJECT_COMPLETE_ID);
        createEmployee(EMPLOYEE_NO_PROJECT, "SOA", "T", "22334455","2. Senior programmer", "Piet Beschikbaar",null);
    }


    private void createEmployee(final int id, final String cell, final String cluster, final String employeeNumber, final String level, final String longName, final Integer project_id) {
        String insertEmployee = "insert into to_employee(id,cell,cluster,employeeNumber,level,longName";
        if (project_id != null) {
            insertEmployee += ",project_id";
        }
        insertEmployee += ") values(?,?,?,?,?,?";
        if (project_id != null) {
            insertEmployee += ",?";
        }
        insertEmployee += ");";
        getJdbcTemplate().execute(insertEmployee, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setInt(1,id);
                preparedStatement.setString(2, cell);
                preparedStatement.setString(3, cluster);
                preparedStatement.setString(4, employeeNumber);
                preparedStatement.setString(5, level);
                preparedStatement.setString(6, longName);
                if (project_id != null) {
                    preparedStatement.setInt(7, project_id);
                }
                preparedStatement.execute();
                return null;
            }
        });
    }

    private void createProject(final int id, final String client, final String managerEmail, final String managerName, final String project, final String wbs) {
        String insertProject = "insert into to_project (id,client,managerEmail,managerName,name,wbs) values(?,?,?,?,?,?);";
        getJdbcTemplate().execute(insertProject, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setInt(1,id);
                preparedStatement.setString(2, client);
                preparedStatement.setString(3, managerEmail);
                preparedStatement.setString(4, managerName);
                preparedStatement.setString(5, project);
                preparedStatement.setString(6, wbs);
                preparedStatement.execute();
                return null;
            }
        });
    }

    private void createTraining(final int id, final String code, final String name, final String remark) {
        String insert = "insert into to_training (id,code,name,remark) values(?,?,?,?);";
        getJdbcTemplate().execute(insert, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setInt(1,id);
                preparedStatement.setString(2, code);
                preparedStatement.setString(3, name);
                preparedStatement.setString(4, remark);
                preparedStatement.execute();
                return null;
            }
        });
    }

    private void createTrainingStatus(final int id, final EmployeeTrainingRelation currentRelation, final int employeeId, final int trainingId) {
        String insert = "insert into to_trainingstatus (id,currentRelation,employee_id,training_id) values(?,?,?,?);";
        getJdbcTemplate().execute(insert, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setInt(1,id);
                preparedStatement.setString(2, currentRelation.toString());
                preparedStatement.setInt(3, employeeId);
                preparedStatement.setInt(4, trainingId);
                preparedStatement.execute();
                return null;
            }
        });
    }

    private void createTrainingSession(final int id, final SessionStatus status, final int weekNr, final int trainingId) {
        String insert = "insert into to_trainingsession (id,status,weekNr,training_id) values(?,?,?,?);";
        getJdbcTemplate().execute(insert, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setInt(1,id);
                preparedStatement.setString(2, status.toString());
                preparedStatement.setInt(3, weekNr);
                preparedStatement.setInt(4, trainingId);
                preparedStatement.execute();
                return null;
            }
        });
    }

    private void createTrainingPlanning(final int id, final int employeeId, final int trainingSessionsId) {
        String insert = "insert into to_trainingplanning (id,employee_id,trainingsession_id) values(?,?,?);";
        getJdbcTemplate().execute(insert, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setInt(1,id);
                preparedStatement.setInt(2, employeeId);
                preparedStatement.setInt(3, trainingSessionsId);
                preparedStatement.execute();
                return null;
            }
        });
    }

}
