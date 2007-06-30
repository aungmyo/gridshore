package nl.gridshore.samples.springws.server;

import nl.gridshore.samples.jaxb.CongressRegistrationRequest;
import nl.gridshore.samples.jaxb.CongressRegistrationResponse;
import nl.gridshore.samples.jaxb.ObjectFactory;

import org.springframework.ws.server.endpoint.adapter.MarshallingMethodEndpointAdapter;

public class JaxbMarshallingMethodEndpoint extends MarshallingMethodEndpointAdapter {
	public CongressRegistrationResponse handleCongressRegistrationRequest(CongressRegistrationRequest request) {
		System.out.println("**************"+request.getRegistrant().getEmail());
		ObjectFactory objectFactory = new ObjectFactory();
		CongressRegistrationResponse response = objectFactory.createCongressRegistrationResponse();
		response.setRegistrationCode("code333");
		return response;
	}
}
