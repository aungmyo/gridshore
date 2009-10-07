package nl.gridshore.newsfeed.integration.xmpp;

import com.google.appengine.api.xmpp.Message;

/**
 * @author Jettro Coenradie
 */
public interface XmppMessagingService {
    public void sendMessage(String receiver, String message);

    void handleReceivedMessage(Message receiveMessage);
}
