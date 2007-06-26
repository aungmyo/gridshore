package nl.gridshore.samples.springws.integration.converter;

import java.util.List;

import nl.gridshore.samples.jaxb.Congress;
import nl.gridshore.samples.jaxb.CongressRegistrationRequest;
import nl.gridshore.samples.jaxb.ObjectFactory;
import nl.gridshore.samples.jaxb.Registrant;
import nl.gridshore.samples.jaxb.Session;
import nl.gridshore.samples.jaxb.Sessions;
import nl.gridshore.samples.springws.domain.RegistrationDetails;

public class CongressRegistrationRequestConverter implements Converter<CongressRegistrationRequest,RegistrationDetails> {

	public CongressRegistrationRequest convert(final RegistrationDetails toBeConverted) {
		final ObjectFactory jaxbFactory = new ObjectFactory();
		
		final CongressRegistrationRequest registrationRequest = jaxbFactory.createCongressRegistrationRequest();
		registrationRequest.setCongress(getCongress(jaxbFactory, toBeConverted));
		registrationRequest.setRegistrant(getRegistrant(jaxbFactory, toBeConverted));
		registrationRequest.setSessions(getSessions(jaxbFactory, toBeConverted));
		return registrationRequest;
	}

	private Registrant getRegistrant(ObjectFactory jaxbFactory, RegistrationDetails toBeConverted) {
		final nl.gridshore.samples.springws.domain.Registrant toBeConvertedRegistrant = 
			toBeConverted.getRegistrant();
		Registrant jaxbRegistrant = jaxbFactory.createRegistrant();
		jaxbRegistrant.setFirstName(toBeConvertedRegistrant.getFirstname());
		jaxbRegistrant.setMiddleName(toBeConvertedRegistrant.getMiddlename());
		jaxbRegistrant.setLastName(toBeConvertedRegistrant.getLastname());
		jaxbRegistrant.setEmail(toBeConvertedRegistrant.getEmailAddress());
		return jaxbRegistrant;
	}

	private Congress getCongress(ObjectFactory jaxbFactory, RegistrationDetails toBeConverted) {
		Congress jaxbCongress = jaxbFactory.createCongress();
		jaxbCongress.setCongressId(toBeConverted.getCongressRegisteredFor().getId());
		jaxbCongress.setCongressName(toBeConverted.getCongressRegisteredFor().getName());
		return jaxbCongress;
	}

	private Sessions getSessions(ObjectFactory jaxbFactory, RegistrationDetails toBeConverted) {
		Sessions jaxbSessions = jaxbFactory.createSessions();
		List<nl.gridshore.samples.springws.domain.Session> toBeConvertedSessions = toBeConverted.getSessions();
		for (nl.gridshore.samples.springws.domain.Session toBeConvertedSession : toBeConvertedSessions) {
			jaxbSessions.getSession().add(getSession (jaxbFactory,toBeConvertedSession));
			
		}
		return jaxbSessions;
	}

	private Session getSession (
			ObjectFactory jaxbFactory, nl.gridshore.samples.springws.domain.Session toBeConvertedSession) {
		Session jaxbSession = jaxbFactory.createSession();
		jaxbSession.setSessionId(toBeConvertedSession.getId());
		jaxbSession.setSessionName(toBeConvertedSession.getName());
		return jaxbSession;
	}

}
