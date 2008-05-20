package nl.gridshore.demo.webservice;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.security.SslSocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.jetty.bio.SocketConnector;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Server server = new Server();
        SocketConnector connector = new SocketConnector();
        connector.setPort(8080);
        server.setStopAtShutdown(true);

        SslSocketConnector sslConnector = new SslSocketConnector();
        sslConnector.setPort(8443);
        sslConnector.setKeystore("provider/src/main/resources/WSKeyStore.jks");
        sslConnector.setKeystoreType("JKS");
        sslConnector.setKeyPassword("mypassword");

        server.setConnectors(new Connector[]{connector, sslConnector});

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setServer(server);
        webAppContext.setContextPath("/");
        webAppContext.setWar("provider/src/main/webapp");

        server.addHandler(webAppContext);


         try {
            System.out.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
            server.start();
            System.in.read();
            server.stop();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(100);
        }
    }
}
