package nl.gridshore.demo.webservice;

import org.springframework.ws.server.endpoint.AbstractMarshallingPayloadEndpoint;
import nl.gridshore.schema.myservice.MyServiceResponseType;
import nl.gridshore.schema.myservice.MyServiceRequest;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.math.BigInteger;

public class MyServiceImplementation extends AbstractMarshallingPayloadEndpoint {

    protected Object invokeInternal(final Object o) throws Exception {
        return new JAXBElement<MyServiceResponseType>(new QName("http://www.gridshore.nl/schema/myservice", "MyServiceResponse"), MyServiceResponseType.class, handle((MyServiceRequest) o));
    }

    protected MyServiceResponseType handle(final MyServiceRequest request) throws Exception {
        MyServiceResponseType response = new MyServiceResponseType();
        response.setData(new MyServiceResponseType.Data());
        response.getData().setId(request.getID());
        response.getData().setValue("My value");
        return response;
    }
}
