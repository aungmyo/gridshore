package nl.gridshore.newsfeed.web;

import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import nl.gridshore.newsfeed.integration.xmpp.XmppMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jettro Coenradie
 */
@Controller
public class XmppMessageReceiverController {

    @Autowired
    private XmppMessagingService xmppMessagingService;

    @RequestMapping(value = "/_ah/xmpp/message/chat/", method = RequestMethod.POST)
    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        XMPPService xmpp = XMPPServiceFactory.getXMPPService();
        Message message = xmpp.parseMessage(request);

        xmppMessagingService.handleReceivedMessage(message);
    }

}
