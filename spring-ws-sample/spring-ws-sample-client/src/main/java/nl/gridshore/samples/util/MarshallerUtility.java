package nl.gridshore.samples.util;

import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Utitlity class to marshall a JAXB object to a string
 * 
 * @author Jettro.Coenradie
 */
public class MarshallerUtility {
	/**
	 * Marshalls the provided object using jaxb to a string
	 * @param jaxbObject A jaxb object to be marshalled
	 * @return String representation of the provided object
	 */
	public static String marshallEvent(Object jaxbObject) {
		String returnString = "";
		try {
			JAXBContext context = JAXBContext.newInstance(jaxbObject.getClass()
					.getPackage().getName());
			Writer responseString = new StringWriter();
			context.createMarshaller().marshal(jaxbObject, responseString);
			returnString = responseString.toString();
		} catch (JAXBException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		}
		return returnString;
	}
}
