package nl.gridshore.newsfeed.web;

import nl.gridshore.newsfeed.integration.mail.MailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Jettro Coenradie
 */
@Controller
public class MailMessageReceiver {
    private final static Logger log = Logger.getLogger(MailMessageReceiver.class);

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/mail/{toEmail}", method = RequestMethod.POST)
    public String receive(@PathVariable String toEmail, HttpServletRequest request)
            throws IOException, MessagingException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session, request.getInputStream());
        String subject = message.getSubject();

        log.debug("Received a message from " + message.getFrom()[0].toString());

        mailService.sendMailFromAdmin(message.getFrom()[0].toString(), "RE : " + subject, "Thank you for your message, we'll get back to you" +
                "as soon as possible.");

        return "empty";
    }
}
