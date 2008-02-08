package nl.gridshore.samples.training.web.controllers.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import nl.gridshore.samples.training.business.EmployeeService;
import nl.gridshore.samples.training.domain.Employee;
import nl.gridshore.samples.training.domain.Project;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Feb 5, 2008
 * Time: 11:17:22 PM
 * Controller used to present the form and respond to the POST with some data
 */
@Controller
@RequestMapping("/searchemployees.view")
public class SearchEmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public SearchEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String prepareSearchForm(ModelMap modelMap) {
        modelMap.addAttribute(new Employee());
        return "searchEmployeeForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitSearchRequest(ModelMap modelMap,
                                      @ModelAttribute("employee")Employee employee,
                                      BindingResult bindingResult) {
        List<Employee> foundEmployees = employeeService.searchEmployees(employee);
        modelMap.addAttribute("employeesList",foundEmployees);
        modelMap.addAttribute(employee);
        return "searchEmployeeForm";
    }
}
