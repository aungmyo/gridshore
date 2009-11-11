package nl.gridshore.newsfeed.web.controllers;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jettro Coenradie
 */
@Controller
public class CreateTaskController {

    @RequestMapping(value = "/task")
    public String createNewQueueItem(ModelMap modelmap) {
        Queue queue = QueueFactory.getQueue("send-mail-queue");
        queue.add(param("fromMail", "mijnmail@gridshore.nl")
                .param("fromName","Jettro Coenradie")
                .param("subject","New task to send mail")
                .param("body","The contents of the mail to be send using a task."));

        modelmap.addAttribute("title", "created task");
        modelmap.addAttribute("message", "You have just created a task to send an email");
        return "message";
    }

}
