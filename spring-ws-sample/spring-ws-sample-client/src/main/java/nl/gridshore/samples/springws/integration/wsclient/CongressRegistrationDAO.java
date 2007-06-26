package nl.gridshore.samples.springws.integration.wsclient;

import nl.gridshore.samples.springws.domain.RegistrationDetails;

public interface CongressRegistrationDAO {
	public String registerForCongress(RegistrationDetails registrationDetails);
}
