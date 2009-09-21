package nl.gridshore.newsfeed.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * @author Jettro Coenradie
 */
@Controller
public class HomeController {
    @RequestMapping("/home")
    protected String homeHandler() {
        return "home";
    }
    
}
