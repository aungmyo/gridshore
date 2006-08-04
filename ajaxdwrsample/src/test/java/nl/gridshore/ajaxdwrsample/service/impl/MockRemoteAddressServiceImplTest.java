package nl.gridshore.ajaxdwrsample.service.impl;

import java.util.List;

import nl.gridshore.ajaxdwrsample.to.AddressTO;
import junit.framework.TestCase;

public class MockRemoteAddressServiceImplTest extends TestCase {
	private MockRemoteAddressServiceImpl remoteAddressService;
	
	protected void setUp() throws Exception {
		remoteAddressService = new MockRemoteAddressServiceImpl();
		remoteAddressService.init();
	}

	public void testFindAddressByPostalCode() {
		AddressTO foundAddressTO = remoteAddressService.findAddressByPostalCode("1234DD");
		assertEquals("The found street is not as expected","streetfour", foundAddressTO.getStreetname());
		assertEquals("The found house number is not as expected","34", foundAddressTO.getHouseNumber());
		assertEquals("The found city is not as expected","cityone", foundAddressTO.getCity());
		assertEquals("The found postal code is not as expected","1234DD", foundAddressTO.getPostalCode());
	}

	public void testFindCompletePostalCodes() {
		List postalCodes = remoteAddressService.findCompletePostalCodes("12");
		assertEquals("Number of postal codes is not as expected",10, postalCodes.size());
	}

}
