package nl.gridshore.newsfeed.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: jettrocoenradie
 * Date: Oct 4, 2009
 * Time: 11:52:50 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HomeController {
    @RequestMapping("/home")
    public String welcome() {
        System.out.println("Yep in the controller");
        return "home";
    }
}
