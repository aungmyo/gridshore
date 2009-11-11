package nl.gridshore.newsfeed.web.receivers;

import nl.gridshore.newsfeed.integration.mail.MailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jettro Coenradie
 */
@Controller
public class TaskReceiverController {
    private static final Logger log = Logger.getLogger(TaskReceiverController.class);

    private MailService mailService;

    @Autowired
    public TaskReceiverController(MailService mailService) {
        this.mailService = mailService;
    }

    @RequestMapping(value = "/queue/send-mail-queue", method = RequestMethod.POST)
    public void receiveTask(@RequestParam("fromMail") String fromMail,
                            @RequestParam("fromName") String fromName,
                            @RequestParam("subject") String subject,
                            @RequestParam("body") String body,
                            HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug("Received a task to send a mail from : " + fromMail);
            log.debug("Name of sender : " + fromName);
            log.debug("Subject : " + subject);
            log.debug("Body : " + body);
        }

        mailService.sendMailToAdmin(fromName,fromMail,subject,body);

        response.setStatus(HttpServletResponse.SC_OK);
    }

}
