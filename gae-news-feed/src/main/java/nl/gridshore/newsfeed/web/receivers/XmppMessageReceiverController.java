package nl.gridshore.newsfeed.web.receivers;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import nl.gridshore.newsfeed.integration.xmpp.XmppMessagingService;
import nl.gridshore.newsfeed.service.ReceivedMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Jettro Coenradie
 */
@Controller
public class XmppMessageReceiverController {

    @Autowired
    private XmppMessagingService xmppMessagingService;

    @Autowired
    private ReceivedMessageService receivedMessageService;

    @RequestMapping(value = "/_ah/xmpp/message/chat/", method = RequestMethod.POST)
    public String create(HttpServletRequest request) throws IOException {
        // TODO check response.setStatus(OK)
        XMPPService xmpp = XMPPServiceFactory.getXMPPService();
        Message message = xmpp.parseMessage(request);

        JID fromJid = message.getFromJid();
        String body = message.getBody();

        receivedMessageService.createReceivedMessage(fromJid.getId(),body);
        xmppMessagingService.sendMessage(fromJid,"Thank you for your response");

        return "empty";
    }

}
