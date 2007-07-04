package nl.gridshore.samples.springws.integration.wsclient.impl;

import nl.gridshore.samples.springws.domain.RegistrationDetails;
import nl.gridshore.samples.springws.integration.converter.Converter;
import nl.gridshore.samples.springws.integration.wsclient.CongressRegistrationDAO;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

/**
 * @author Jettro.Coenradie
 *
 */
public class CongressRegistrationGateway extends WebServiceGatewaySupport implements CongressRegistrationDAO {
	private final Converter<Object,RegistrationDetails> requestConverter;
	private final Converter<String,Object> responseConverter;
	
	/**
	 * Default constructor containing a reference to the request and response converters
	 * @param requestConverter Converter used to convert the request
	 * @param responseConverter Converter used to convert the response
	 */
	public CongressRegistrationGateway(Converter<Object,RegistrationDetails> requestConverter,
			Converter<String,Object> responseConverter) {
		this.requestConverter = requestConverter;
		this.responseConverter = responseConverter;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String registerForCongress(final RegistrationDetails registrationDetails) {
		Object request = requestConverter.convert(registrationDetails);
		Object response = getWebServiceTemplate().marshalSendAndReceive(request);
		return responseConverter.convert(response);
	}

}
