package nl.gridshore.newsfeed.integration.mail.impl;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.*;
import java.util.Arrays;
import java.util.Properties;

/**
 * <p>Special implementation of the JavaMailSenderImpl from the spring framework to make the spring mail sending
 * work on google app engine. Check the following post, this is where I got it from.</p>
 *
 * http://peterbacklund.blogspot.com/2009/04/running-spring-on-google-app-engine.html
 *
 * @author Jettro Coenradie
 */
public class GaeJavaMailSender extends JavaMailSenderImpl {
    @Override
    public Session getSession() {
        return Session.getDefaultInstance(new Properties(), null);
    }

    @Override
    protected Transport getTransport(Session session) throws NoSuchProviderException {
        return new Transport(session, null) {
            @Override
            public void connect(String host, int port, String username, String password) throws MessagingException {
                // Noop
            }

            @Override
            public void close() throws MessagingException {
                // Noop
            }

            @Override
            public void sendMessage(Message message, Address[] recipients) throws MessagingException {
                Transport.send(message, recipients);
            }
        };
    }
}
