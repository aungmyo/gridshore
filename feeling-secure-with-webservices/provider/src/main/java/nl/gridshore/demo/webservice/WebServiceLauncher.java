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
public class WebServiceLauncher
{
    public static void main( String[] args )
    {
        String WSKeyStore = "provider/src/main/resources/WSKeyStore.jks";

        Server server = new Server();
        SocketConnector connector = new SocketConnector();
        connector.setPort(8080);
        server.setStopAtShutdown(true);

        SslSocketConnector sslConnector = new SslSocketConnector();
        sslConnector.setPort(8443);
        sslConnector.setKeystore(WSKeyStore);
        sslConnector.setKeystoreType("JKS");
        sslConnector.setKeyPassword("mypassword");

        server.setConnectors(new Connector[]{connector, sslConnector});

        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setServer(server);
        webAppContext.setContextPath("/");
        webAppContext.setWar("provider/src/main/webapp");

        server.addHandler(webAppContext);


         try {
            System.out.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ENTER TO STOP");
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
