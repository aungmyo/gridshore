package nl.gridshore.newsfeed.integration.xmpp;

import com.google.appengine.api.xmpp.dev.LocalXmppService;
import com.google.appengine.tools.development.ApiProxyLocalImpl;
import com.google.apphosting.api.ApiProxy;
import nl.gridshore.TestEnvironment;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

/**
 * @author Jettro Coenradie
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class XmppMessagingServiceTest {
    private static LocalXmppService xmppService;

    @Autowired
    private XmppMessagingService xmppMessagingService;

    @BeforeClass
    public static void init() {
        ApiProxy.setEnvironmentForCurrentThread(new TestEnvironment());
        ApiProxy.setDelegate(new ApiProxyLocalImpl(new File("./target")) {
        });
        ApiProxyLocalImpl proxy = (ApiProxyLocalImpl) ApiProxy.getDelegate();
        xmppService = (LocalXmppService)proxy.getService("xmpp");
        xmppService.start();
    }

    @Test
    public void sendMessage() {
        xmppMessagingService.sendMessage("gridshore","This is the message");

    }

    @AfterClass
    public static void tearDown() {
        ApiProxy.setDelegate(null);
        ApiProxy.setEnvironmentForCurrentThread(null);
        xmppService.stop();
    }
}
