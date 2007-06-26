package nl.gridshore.samples.springws.integration.wsclient.impl;

import nl.gridshore.samples.springws.domain.RegistrationDetails;
import nl.gridshore.samples.springws.integration.converter.Converter;
import nl.gridshore.samples.springws.integration.wsclient.CongressRegistrationDAO;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CongressRegistrationGateway extends WebServiceGatewaySupport implements CongressRegistrationDAO {
	private final Converter<Object,RegistrationDetails> requestConverter;
	private final Converter<String,Object> responseConverter;
	
	public CongressRegistrationGateway(Converter<Object,RegistrationDetails> requestConverter,
			Converter<String,Object> responseConverter) {
		this.requestConverter = requestConverter;
		this.responseConverter = responseConverter;
	}
	
	public String registerForCongress(final RegistrationDetails registrationDetails) {
		Object request = requestConverter.convert(registrationDetails);
		Object response = getWebServiceTemplate().marshalSendAndReceive(request);
		return responseConverter.convert(response);
	}

}
