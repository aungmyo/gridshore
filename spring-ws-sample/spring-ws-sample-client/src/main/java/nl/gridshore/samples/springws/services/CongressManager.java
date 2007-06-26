package nl.gridshore.samples.springws.services;

import nl.gridshore.samples.springws.domain.Registrant;

public interface CongressManager {
	public void createCongressRegistration(Integer congressId, Registrant registrant,Integer[] sessionIds);
}
