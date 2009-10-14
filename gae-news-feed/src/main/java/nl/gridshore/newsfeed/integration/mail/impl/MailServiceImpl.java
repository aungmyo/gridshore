package nl.gridshore.newsfeed.integration.mail.impl;

import nl.gridshore.newsfeed.integration.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 
 *
 * @author Jettro Coenradie
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMailToAdmin(String name, String email, String title, String message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("jettro.coenradie@gmail.com");
            mailMessage.setTo("jettro@coenradie.com");
            mailMessage.setText(message);
            mailMessage.setSubject(title);
            this.mailSender.send(mailMessage);
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void sendMailFromAdmin(String toMail, String subject, String message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("jettro.coenradie@gmail.com");
            mailMessage.setTo(toMail);
            mailMessage.setText(message);
            mailMessage.setSubject(subject);
            this.mailSender.send(mailMessage);
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
