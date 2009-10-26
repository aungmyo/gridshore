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

        queue.add(param("toMail", "jettro@coenradie.com"));
        modelmap.addAttribute("title", "created task");
        modelmap.addAttribute("message", "You have just created a task");
        return "message";
    }

}
