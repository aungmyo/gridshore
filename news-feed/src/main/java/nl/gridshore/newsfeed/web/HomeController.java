package nl.gridshore.newsfeed.web;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jettro Coenradie
 */
public class HomeController {
    @RequestMapping("/home")
    protected String homeHandler() {
        return "home";
    }
    
}
