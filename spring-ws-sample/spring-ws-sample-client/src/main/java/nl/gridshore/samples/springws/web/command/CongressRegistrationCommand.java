package nl.gridshore.samples.springws.web.command;

import nl.gridshore.samples.springws.domain.Registrant;

/**
 * @author Jettro.Coenradie
 *
 * Command object, or Form object, or DTO object containing information about a congress
 * registration request.
 */
public class CongressRegistrationCommand {
	private Integer congressId;
	private Registrant registrant;
	private Integer[] sessionIds;
	
	public Integer getCongressId() {
		return congressId;
	}
	public void setCongressId(Integer congressId) {
		this.congressId = congressId;
	}
	public Registrant getRegistrant() {
		return registrant;
	}
	public void setRegistrant(Registrant registrant) {
		this.registrant = registrant;
	}
	public Integer[] getSessionIds() {
		return sessionIds;
	}
	public void setSessionIds(Integer[] sessionIds) {
		this.sessionIds = sessionIds;
	}
}
