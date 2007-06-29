package nl.gridshore.samples.springws.integration.converter;

/**
 * This is an interface for all converters. Classes can program agains these general interfaces
 * and get the right converter injected. This can for instance be a JAXB request converter. This
 * way the classes that uses the converter does not need to know we use JAXB for instance.
 * @author Jettro.Coenradie
 *
 * @param <E> Type to be returned by the convert method as the converted class
 * @param <T> Type to be provided to the convert method to be used as the class to be converted
 */
public interface Converter<E,T> {
	/**
	 * Template method to be called by the usage class.
	 * 
	 * @param toBeConverted
	 * @return
	 */
	public E convert(T toBeConverted);
}
