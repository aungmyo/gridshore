package nl.gridshore.paymentsample.business.impl;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 29-aug-2007
 * Time: 21:05:57
 * To change this template use File | Settings | File Templates.
 */
public class LoggingThrowable {
    private final static Logger logger = Logger.getLogger(LoggingThrowable.class);

    public void doLogThrowable(RuntimeException runtimeException) {
        logger.info("A runtime exception is thrown by on of the business services",runtimeException);
    }
}
