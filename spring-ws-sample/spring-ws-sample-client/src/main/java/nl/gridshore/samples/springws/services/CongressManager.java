package nl.gridshore.samples.springws.services;

import nl.gridshore.samples.springws.domain.Registrant;

/**
 * @author Jettro.Coenradie
 * Manager object used to do anythng with congresses
 */
public interface CongressManager {
	/**
	 * Creates a new congress registration, if the provided registrant cannot be found it is 
	 * created as well. If the id of the congress or one of the sessions cannot be found an error is thrown.
	 * @param congressId Integer representing the id of the congress to register for
	 * @param registrant Registrant to register for the congress and sessions
	 * @param sessionIds Integer[] array of integers representing the id's of session to register for
	 */
	public void createCongressRegistration(Integer congressId, Registrant registrant,Integer[] sessionIds);
}
