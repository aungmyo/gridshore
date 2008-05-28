package nl.gridshore.demo.webservice;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import nl.gridshore.demo.webservice.client.WSClientImplementation;

import javax.xml.soap.SOAPException;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class WsClient 
{
    public static void main( String[] args ) throws IOException, SOAPException {
        // defining a custom truststore for the JRE, since our certificate isn't accepted by default
        System.setProperty("javax.net.ssl.trustStorePassword","password");
        System.setProperty("javax.net.ssl.trustStore", "c:/development/workspaces/ws-demo/client/src/main/resources/trustStore.jks");

        // URL where the web service provider is running.
        System.setProperty("endpointUrl", "https://localhost:8443/ordersService");        

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ws-client-context.xml");
        context.start();

        WSClientImplementation wsClient = (WSClientImplementation) context.getBean("wsClientImplementation");
        System.out.println(wsClient.doCall());
    }
}