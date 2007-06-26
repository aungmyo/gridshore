package nl.gridshore.samples.springws.integration.converter;

public interface Converter<E,T> {
	public E convert(T toBeConverted);
}
