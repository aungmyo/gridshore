package nl.gridshore.newsfeed.integration.xmpp;

import com.google.appengine.api.xmpp.JID;

/**
 * Service used to handle Xmpp (Google talk) message and send message back. To be able to send messages to the
 * receiver the receiver must be known.
 *
 * @author Jettro Coenradie
 */
public interface XmppMessagingService {
    /**
     * Send the provided message to the provided receiver using the xmpp protocol.
     * @param receiver String containing the id of the receiver for the message
     * @param message String containing the message to send.
     */
    public void sendMessage(String receiver, String message);

    void sendMessage(JID jid, String message);
}
