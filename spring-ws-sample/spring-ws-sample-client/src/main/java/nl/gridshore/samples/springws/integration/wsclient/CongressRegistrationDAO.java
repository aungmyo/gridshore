package nl.gridshore.samples.springws.integration.wsclient;

import nl.gridshore.samples.springws.domain.RegistrationDetails;

/**
 * @author Jettro.Coenradie
 * Congress Registration Dataaccess
 */
public interface CongressRegistrationDAO {
	/**
	 * Register the provided details for a congress
	 * @param registrationDetails RegistrationDetails that contains all data needed to register 
	 * 		for a congress
	 * @return String response containing the registration number
	 */
	public String registerForCongress(RegistrationDetails registrationDetails);
}
