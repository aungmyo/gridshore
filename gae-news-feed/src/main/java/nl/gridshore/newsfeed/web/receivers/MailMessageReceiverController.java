package nl.gridshore.newsfeed.web.receivers;

import nl.gridshore.newsfeed.integration.mail.MailService;
import nl.gridshore.newsfeed.service.ReceivedMessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Jettro Coenradie
 */
@Controller
public class MailMessageReceiverController {
    private final static Logger log = Logger.getLogger(MailMessageReceiverController.class);

    @Autowired
    private MailService mailService;

    @Autowired
    private ReceivedMessageService receivedMessageService;

    @RequestMapping(value = "/mail/{toEmail}", method = RequestMethod.POST)
    public String receive(@PathVariable String toEmail, HttpServletRequest request)
            throws IOException, MessagingException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session, request.getInputStream());
        String subject = message.getSubject();

        String mailFrom = message.getFrom()[0].toString();

        String content = obtainContentFromMail(message);
        if (log.isDebugEnabled()) {
            log.debug("Received a message from " + mailFrom);
            log.debug("Message contains : " + content);
        }

        receivedMessageService.createReceivedMessage(mailFrom,content);

        mailService.sendMailFromAdmin(mailFrom, "RE : " + subject, "Thank you for your message, we'll get back to you" +
                "as soon as possible.");

        return "empty";
    }

    private String obtainContentFromMail(MimeMessage message) throws MessagingException, IOException {
        String content = "";
        if (message.getContent() instanceof MimeMultipart) {
            MimeMultipart multipart = (MimeMultipart)message.getContent();
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart part = multipart.getBodyPart(i);
                log.debug("Content type of received mail is : " + part.getContentType());
                if (part.getContentType().contains("text/plain") || part.getContentType().contains("text/html")) {
                    content = part.getContent().toString();
                    break;
                } else {
                    log.debug("Obtained a part that is not text plain or html, but : " + part.getContentType());
                }
            }
        } else {
            content = message.getContent().toString();
        }
        return content;
    }
}
