package nl.gridshore.demo.webservice.client;

import org.springframework.ws.client.core.WebServiceTemplate;
import org.w3c.dom.Element;
import nl.gridshore.schema.myservice.MyServiceRequest;
import nl.gridshore.schema.myservice.MyServiceResponseType;

import javax.xml.bind.JAXBElement;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPException;
import java.math.BigInteger;

public class WSClientImplementation {

    WebServiceTemplate wsTemplate;

    public String doCall() throws SOAPException {
        MyServiceRequest request = new MyServiceRequest();
        request.setID(new BigInteger("123"));
        request.setOtherElement(SOAPFactory.newInstance().createElement("Bugger"));
        MyServiceResponseType response = ((JAXBElement<MyServiceResponseType>) wsTemplate.marshalSendAndReceive(request)).getValue();
        return response.getData().getValue();
    }

    public void setWsTemplate(final WebServiceTemplate wsTemplate) {
        this.wsTemplate = wsTemplate;
    }
}
