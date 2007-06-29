package nl.gridshore.samples.springws.integration.converter;

import nl.gridshore.samples.jaxb.CongressRegistrationResponse;

/**
 * @author Jettro.Coenradie
 *
 */
public class CongressRegistrationResponseConverter implements Converter<String, CongressRegistrationResponse> {

	/**
	 * {@inheritDoc}
	 */
	public String convert(CongressRegistrationResponse toBeConverted) {
		String response = toBeConverted.getRegistrationCode();
		return response;
	}

}
