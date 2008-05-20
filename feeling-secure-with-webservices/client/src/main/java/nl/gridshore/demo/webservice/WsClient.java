package nl.gridshore.demo.webservice;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import nl.gridshore.demo.webservice.client.WSClientImplementation;

import javax.net.ssl.SSLContext;
import javax.xml.soap.SOAPException;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class WsClient 
{
    public static void main( String[] args ) throws IOException, SOAPException {
//        String trustStore = WsClient.class.getClassLoader().getResource("classpath:trustStore.jks").getFile();
//        String trustStore = new ClassPathResource("trustStore.jks").getURL().toString();
        System.setProperty("javax.net.ssl.trustStorePassword","password");
        System.setProperty("javax.net.ssl.trustStore", "/development/workspaces/ws-demo/client/src/main/resources/trustStore.jks");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ws-client-context.xml");
        context.start();

        WSClientImplementation wsClient = (WSClientImplementation) context.getBean("wsClientImplementation");
        System.out.println(wsClient.doCall());
    }
}