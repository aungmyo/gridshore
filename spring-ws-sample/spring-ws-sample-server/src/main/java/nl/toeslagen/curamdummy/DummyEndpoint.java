/*
 * DummyEndpoint.java
 * 
 * Created on 19-jun-2007, 22:30:58
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package nl.toeslagen.curamdummy;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.xpath.XPath;
import org.springframework.ws.server.endpoint.AbstractJDomPayloadEndpoint;

/**
 *
 * @author Jettro.Coenradie
 */
public class DummyEndpoint extends AbstractJDomPayloadEndpoint {
    private XPath emailAddress;

    public DummyEndpoint() {
        try {
            Namespace namespace = org.jdom.Namespace.getNamespace("cr", "http://dispatcher.congress.osgisamples.com");
            emailAddress = org.jdom.xpath.XPath.newInstance("//cr:Email");
            emailAddress.addNamespace(namespace);
        }
        catch (JDOMException ex) {
            ex.printStackTrace();
        }
    }

    protected Element invokeInternal(Element services) throws Exception {
    	System.out.println(services.getText());
        String type = emailAddress.valueOf(services);
        System.out.println("**************************************"+type);
        return null;
    }

}
