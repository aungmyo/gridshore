package nl.gridshore.samples.training.web.controllers.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import nl.gridshore.samples.training.domain.Employee;
import nl.gridshore.samples.training.business.EmployeeService;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 1, 2008
 * Time: 9:38:17 PM
 * Add planned training to an employee
 */
@Controller
@RequestMapping("/addplannedtraining.view")
public class AddPlannedTrainingController {
    private EmployeeService employeeService;

    @Autowired
    public AddPlannedTrainingController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView processSubmit(
            @RequestParam("employeeId") Long employeeId,
            @RequestParam("trainingsessionId") Long trainingsessionId
            ) {
        Employee employee = employeeService.addTrainingSessionToPlanningOfEmployee(employeeId,trainingsessionId);
        return new ModelAndView("employee","employee",employee);
    }

}
