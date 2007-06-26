package nl.gridshore.samples.springws.services.impl;

import nl.gridshore.samples.springws.domain.Congress;
import nl.gridshore.samples.springws.domain.Registrant;
import nl.gridshore.samples.springws.domain.RegistrationDetails;
import nl.gridshore.samples.springws.domain.Session;
import nl.gridshore.samples.springws.integration.wsclient.CongressRegistrationDAO;
import nl.gridshore.samples.springws.services.CongressManager;

public class CongressManagerImpl implements CongressManager {
	private final CongressRegistrationDAO congressRegistrationDAO;
	
	public CongressManagerImpl(final CongressRegistrationDAO congressRegistrationDAO) {
		this.congressRegistrationDAO = congressRegistrationDAO;
	}
	
	public void createCongressRegistration(Integer congressId,
			Registrant registrant, Integer[] sessionIds) {
		RegistrationDetails registrationDetails = createRegistrationDetails(congressId,registrant,sessionIds);
		String result = congressRegistrationDAO.registerForCongress(registrationDetails);
		System.out.println("***************"+result);
	}

	private RegistrationDetails createRegistrationDetails(Integer congressId,
			Registrant registrant, Integer[] sessionIds) {
		RegistrationDetails registrationDetails = new RegistrationDetails();
		registrationDetails.setRegistrant(registrant);
		Congress tobeRegisteredCongress = new Congress();
		tobeRegisteredCongress.setId(congressId);
		registrationDetails.setCongressRegisteredFor(tobeRegisteredCongress);
		
		for (Integer integer : sessionIds) {
			Session session = new Session();
			session.setId(integer);
			registrationDetails.addSession(session);
		}
		
		return registrationDetails;
	}

}
