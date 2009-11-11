package nl.gridshore.newsfeed.integration.mail.google;

import nl.gridshore.newsfeed.integration.mail.MailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * <p>Default implementation for {@code MailService} using the provided {@code JavaMailSender}</p>
 * <p>Beware that in case of google app engine mail integration, mails can only be send by administrators or logged in
 * users.</p>
 *
 * @author Jettro Coenradie
 */
@Service
public class GoogleMailService implements MailService {
    private static final Logger log = Logger.getLogger(GoogleMailService.class);
    private static final String ADMIN_MAIL_FROM = "jettro.coenradie@gmail.com";
    private static final String ADMIN_MAIL_TO = "jettro@coenradie.com";

    private JavaMailSender mailSender;

    @Autowired
    public GoogleMailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendMailToAdmin(String name, String email, String subject, String message) {
        Assert.hasText(name, "Name of the sender of an email cannot be empty");
        Assert.hasText(email, "Email of the sender of an email cannot be empty");
        Assert.hasText(subject, "Subject of an email cannot be empty");
        Assert.hasText(message, "Message of an email cannot be empty");

        if (log.isDebugEnabled()) {
            log.debug("Sending a mail to admin for : " + email);
        }

        sendEmailMessage(ADMIN_MAIL_TO, ADMIN_MAIL_FROM, subject, message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendMailFromAdmin(String toMail, String subject, String message) {
        Assert.hasText(toMail, "The toMail of an email cannot be empty");
        Assert.hasText(subject, "The Subject of an email cannot be empty");
        Assert.hasText(message, "The message of an email cannot be empty");

        if (log.isDebugEnabled()) {
            log.debug("Sending a mail from admin to : " + toMail);
            log.debug("message : " + message);
        }

        sendEmailMessage(toMail, ADMIN_MAIL_FROM, subject, message);
    }

    private void sendEmailMessage(String toMail, String fromMail, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromMail);
        mailMessage.setTo(toMail);
        mailMessage.setText(message);
        mailMessage.setSubject(subject);
        this.mailSender.send(mailMessage);
    }
}
