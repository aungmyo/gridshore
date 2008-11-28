package nl.gridshore.samples.hippo;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 27, 2008
 * Time: 8:38:18 AM
 * Exception used to indicate an exception in the configuration of this connector in relation to how it is used.
 */
public class ConfigurationException extends RuntimeException {
    public ConfigurationException(String s) {
        super(s);
    }

    public ConfigurationException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
