package nl.gridshore.samples.springws.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDetails {
	private int id;
	private Congress congressRegisteredFor;
	private Registrant registrant;
	private List<Session> sessions = new ArrayList<Session>();
	private BigDecimal registrationCosts;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Congress getCongressRegisteredFor() {
		return congressRegisteredFor;
	}
	public void setCongressRegisteredFor(Congress congressRegisteredFor) {
		this.congressRegisteredFor = congressRegisteredFor;
	}
	public Registrant getRegistrant() {
		return registrant;
	}
	public void setRegistrant(Registrant registrant) {
		this.registrant = registrant;
	}
	public BigDecimal getRegistrationCosts() {
		return registrationCosts;
	}
	public void setRegistrationCosts(BigDecimal registrationCosts) {
		this.registrationCosts = registrationCosts;
	}
	public List<Session> getSessions() {
		return sessions;
	}
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	public void addSession(Session session) {
		this.sessions.add(session);
	}
}
