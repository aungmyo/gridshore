package nl.gridshore.newsfeed.integration.xmpp.impl;

import com.google.appengine.api.xmpp.*;
import nl.gridshore.newsfeed.integration.xmpp.XmppMessagingService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Jettro Coenradie
 */
@Service
public class XmppMessagingServiceImpl implements XmppMessagingService {
    private final static Logger log = Logger.getLogger(XmppMessagingServiceImpl.class);

    @Override
    public void sendMessage(String receiver, String message) {
        JID jid = new JID(receiver);
        sendMessage(jid, message);
    }

    @Override
    public void sendMessage(JID jid, String message) {
        Message msg = new MessageBuilder()
                .withRecipientJids(jid)
                .withBody(message)
                .build();
        boolean messageSent = false;
        XMPPService xmpp = XMPPServiceFactory.getXMPPService();
        if (xmpp.getPresence(jid).isAvailable()) {
            SendResponse status = xmpp.sendMessage(msg);
            messageSent = (status.getStatusMap().get(jid) == SendResponse.Status.SUCCESS);
        }

        if (!messageSent) {
            log.error("Cannot send a message using xmpp to : " + jid.toString());
        }
    }
}
