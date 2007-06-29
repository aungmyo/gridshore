package nl.gridshore.samples.springws.services.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nl.gridshore.samples.springws.domain.Congress;
import nl.gridshore.samples.springws.domain.Registrant;
import nl.gridshore.samples.springws.domain.RegistrationDetails;
import nl.gridshore.samples.springws.domain.Session;
import nl.gridshore.samples.springws.integration.wsclient.CongressRegistrationDAO;
import nl.gridshore.samples.springws.services.CongressManager;

/**
 * @author Jettro.Coenradie
 *
 */
public class CongressManagerImpl implements CongressManager {
	private final Log logger = LogFactory.getLog(CongressManagerImpl.class);
	private final CongressRegistrationDAO congressRegistrationDAO;
	
	/**
	 * Default constructor
	 * @param congressRegistrationDAO Dataaccess component use to find data and make the registration persistent
	 */
	public CongressManagerImpl(final CongressRegistrationDAO congressRegistrationDAO) {
		this.congressRegistrationDAO = congressRegistrationDAO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void createCongressRegistration(Integer congressId,
			Registrant registrant, Integer[] sessionIds) {
		RegistrationDetails registrationDetails = createRegistrationDetails(congressId,registrant,sessionIds);
		String result = congressRegistrationDAO.registerForCongress(registrationDetails);
		if (logger.isInfoEnabled()) {
			logger.info("The registration is persisted"+result);
		}
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
