package nl.gridshore.ajaxdwrsample.service;

import java.util.List;

import nl.gridshore.ajaxdwrsample.to.AddressTO;

/**
 * @author Jettro.Coenradie
 *
 * Service that contains all methods that deal with addresses. Return values are ready
 * to be called remotely by ajax for instance.
 */
public interface RemoteAddressService {
	
	/**
	 * Returns the available address belonging to the postal code provided
	 * 
	 * @param postalCode Complete postal code to search the address for
	 * @return Found address belonging to the postal code
	 */
	public AddressTO findAddressByPostalCode(String postalCode);
	
	/**
	 * Returns the postal codes that make the provided part of the postal code complete
	 * @param partPostalCode
	 * @return List with strings containing postal codes
	 */
	public List findCompletePostalCodes(String partPostalCode);
}
