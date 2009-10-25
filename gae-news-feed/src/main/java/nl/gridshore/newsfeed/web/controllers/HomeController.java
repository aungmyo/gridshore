package nl.gridshore.newsfeed.web.controllers;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for handling the basic home page request
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String welcome(ModelMap model) {
        UserService service = UserServiceFactory.getUserService();
        if (service.isUserLoggedIn()) {
            model.addAttribute("currentUser", service.getCurrentUser().getNickname() + " ");
        } else {
            model.addAttribute("currentUser", "");
        }
        return "home";
    }
}
