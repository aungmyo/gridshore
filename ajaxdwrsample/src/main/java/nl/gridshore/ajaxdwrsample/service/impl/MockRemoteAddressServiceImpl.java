package nl.gridshore.ajaxdwrsample.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nl.gridshore.ajaxdwrsample.service.RemoteAddressService;
import nl.gridshore.ajaxdwrsample.to.AddressTO;

public class MockRemoteAddressServiceImpl implements RemoteAddressService  {
	private Map addressByPostalCode = new HashMap();

	/**
	 * Method to be called to set the addresses and postalcodes
	 */
	public void init() {
		addressByPostalCode.put("1234AA", new AddressTO("streetone","1","cityone","1234AA"));
		addressByPostalCode.put("1234BB", new AddressTO("streettwo","11","cityone","1234BB"));
		addressByPostalCode.put("1234CC", new AddressTO("streetthree","12","cityone","1234CC"));
		addressByPostalCode.put("1234DD", new AddressTO("streetfour","34","cityone","1234DD"));
		addressByPostalCode.put("3344AA", new AddressTO("streetone","67","citytwo","3344AA"));
		addressByPostalCode.put("2266AA", new AddressTO("streetone","1","citythree","2266AA"));
		addressByPostalCode.put("5678KK", new AddressTO("streeteight","12","cityfour","5678KK"));
		addressByPostalCode.put("7755CC", new AddressTO("streetten","16","cityfive","7755CC"));
		addressByPostalCode.put("3344WW", new AddressTO("streetone","101","citysix","3344WW"));
		addressByPostalCode.put("8899SS", new AddressTO("streetfive","1","cityseven","8899SS"));
	}
	
	public AddressTO findAddressByPostalCode(final String postalCode) {
		return (AddressTO)addressByPostalCode.get(postalCode);
	}

	public List findCompletePostalCodes(final String partPostalCode) {
		Set keys = addressByPostalCode.keySet();
		List retKeys = new ArrayList();
		for (Iterator iter = keys.iterator();iter.hasNext();) {
			String key = (String)iter.next();
			retKeys.add(key);
		}
		return retKeys;
	}

}
