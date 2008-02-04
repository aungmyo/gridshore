package nl.gridshore.samples.training.web.controllers.employee;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import nl.gridshore.samples.training.business.EmployeeService;
import nl.gridshore.samples.training.business.TrainingService;
import nl.gridshore.samples.training.domain.Employee;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 30, 2008
 * Time: 8:44:13 AM
 * Controller class that handles all incoming request that do not have to deal with changing data
 */
@Controller
public class EmployeeController {
    private EmployeeService employeeService;
    private TrainingService trainingService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, TrainingService trainingService) {
        this.employeeService = employeeService;
        this.trainingService = trainingService;
    }

    @RequestMapping("/employees.view")
    public ModelMap employeesHandler() {
        return new ModelMap("employeesList",employeeService.obtainAllEmployees());
    }

    @RequestMapping("/employee.view")
    public ModelMap employeeHandler(@RequestParam("employeeId") Long employeeId) {
        return new ModelMap(employeeService.obtainEmployeeById(employeeId));
    }

//    @RequestMapping("/showplannedtraining.view,/showwishedtraining.view")
    @RequestMapping(value = {"/showplannedtraining.view","/showwishedtraining.view"})
    public ModelMap showTrainingPlanForEmployeeHandler(@RequestParam("employeeId") Long employeeId) {
        ModelMap map = new ModelMap(employeeService.obtainEmployeeById(employeeId));
        map.addAttribute("trainingList",trainingService.obtainAllTrainings());
        return map;
    }

//    @RequestMapping("/showwishedtraining.view")
//    public ModelMap showTrainingWishesForEmployeeHandler(@RequestParam("employeeId") Long employeeId) {
//        ModelMap map = new ModelMap(employeeService.obtainEmployeeById(employeeId));
//        map.addAttribute("trainingList",trainingService.obtainAllTrainings());
//        return map;
//    }

}
