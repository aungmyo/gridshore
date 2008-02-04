package nl.gridshore.samples.training.web.controllers.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import nl.gridshore.samples.training.business.EmployeeService;
import nl.gridshore.samples.training.domain.Employee;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 3, 2008
 * Time: 11:00:00 AM
 * Controller used to add a training to he wishlist of an employee
 */
@Controller
@RequestMapping("/addwishedtraining.view")
public class AddWishedTrainingController {
    private EmployeeService employeeService;

    @Autowired
    public AddWishedTrainingController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView processSubmit(
            @RequestParam("employeeId") Long employeeId,
            @RequestParam("trainingId") Long trainingId
            ) {
        Employee employee = employeeService.addTrainingToWishListOfEmployee(employeeId,trainingId);
        return new ModelAndView("employee","employee",employee);
    }

}
