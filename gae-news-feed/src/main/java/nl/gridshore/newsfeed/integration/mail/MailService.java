package nl.gridshore.newsfeed.integration.mail;

/**
 * TODO send mail to my account on behalf of administrator or a registered user
 * 
 * @author Jettro Coenradie
 */
public interface MailService {
    void sendMailToAdmin(String name, String email, String title, String message);
    void sendMailFromAdmin(String toMail, String subject, String message);
}
