package nl.gridshore.newsfeed.integration.mail;

/**
 * Mail service used to actually send the messages using email.
 * 
 * @author Jettro Coenradie
 */
public interface MailService {
    /**
     * Use to send a mail to the administrator from the provided email address. Only plain text emails are
     * supported.
     *
     * @param name String contaning the name of the person sending the mail
     * @param fromEmail String email containing the email of the person sending the mail
     * @param subject String title of the mail
     * @param message String the message of the mail
     */
    void sendMailToAdmin(String name, String fromEmail, String subject, String message);

    /**
     * Use to send an email to the provided email address send by the administrator.
     *
     * @param toEmail String containing the email address to send a message to
     * @param subject String containing the subject of the email
     * @param message String containing the contents of the email
     */
    void sendMailFromAdmin(String toEmail, String subject, String message);
}
