package nl.gridshore.paymentsample.business.impl;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 29-aug-2007
 * Time: 20:47:20
 * To change this template use File | Settings | File Templates.
 */
public class LoggingMethodEntry {
    private final static Logger logger = Logger.getLogger(LoggingMethodEntry.class);

    public void doLogMethodEntry(JoinPoint pjp) {
        logger.info("Processed: " + pjp.getSignature().toShortString());
    }
}
