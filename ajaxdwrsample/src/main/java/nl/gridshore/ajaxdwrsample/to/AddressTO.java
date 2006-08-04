package nl.gridshore.ajaxdwrsample.to;

import java.io.Serializable;

/**
 * @author Jettro.Coenradie
 *
 * Transferobject representing the address object
 */
public class AddressTO implements Serializable {
	private static final long serialVersionUID = -3412614511104530857L;

	private String streetname;
	private String houseNumber;
	private String city;
	private String postalCode;
	
	public AddressTO(final String streetname,final String houseNumber, final String city, final String postalCode) {
		this.streetname = streetname;
		this.houseNumber = houseNumber;
		this.city = city;
		this.postalCode = postalCode;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(final String city) {
		this.city = city;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(final String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getStreetname() {
		return streetname;
	}
	public void setStreetname(final String streetname) {
		this.streetname = streetname;
	}
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((city == null) ? 0 : city.hashCode());
		result = PRIME * result + ((houseNumber == null) ? 0 : houseNumber.hashCode());
		result = PRIME * result + ((postalCode == null) ? 0 : postalCode.hashCode());
		result = PRIME * result + ((streetname == null) ? 0 : streetname.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AddressTO other = (AddressTO) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (houseNumber == null) {
			if (other.houseNumber != null)
				return false;
		} else if (!houseNumber.equals(other.houseNumber))
			return false;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equals(other.postalCode))
			return false;
		if (streetname == null) {
			if (other.streetname != null)
				return false;
		} else if (!streetname.equals(other.streetname))
			return false;
		return true;
	}

}
