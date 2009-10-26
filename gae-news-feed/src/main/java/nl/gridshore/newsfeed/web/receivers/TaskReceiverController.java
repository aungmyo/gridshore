package nl.gridshore.newsfeed.web.receivers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Jettro Coenradie
 */
@Controller
public class TaskReceiverController {

    @RequestMapping(value = "/queue/send-mail-queue", method = RequestMethod.POST)
    public void receiveTask(@RequestParam("toMail") String toMail, HttpServletResponse response) {
        System.out.println("Received a task to send a mail : " + toMail);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
