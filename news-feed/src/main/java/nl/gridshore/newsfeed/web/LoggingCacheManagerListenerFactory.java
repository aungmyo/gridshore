package nl.gridshore.newsfeed.web;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Status;
import net.sf.ehcache.event.CacheManagerEventListener;
import net.sf.ehcache.event.CacheManagerEventListenerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author Jettro Coenradie
 */
public class LoggingCacheManagerListenerFactory extends CacheManagerEventListenerFactory {
    private static final Logger log = LoggerFactory.getLogger(CacheManagerEventListener.class);

    public LoggingCacheManagerListenerFactory() {
        log.debug("Creating the LoggingCacheManagerFactory");
    }

    @Override
    public CacheManagerEventListener createCacheManagerEventListener(Properties properties) {
        return new CacheManagerEventListener() {
            private Status status = Status.STATUS_UNINITIALISED;
            @Override
            public void init() throws CacheException {
                status = Status.STATUS_ALIVE;
                log.debug("Initialize the listener factory");
            }

            @Override
            public Status getStatus() {
                log.debug("Return the status of the cache manager listener");
                return status;
            }

            @Override
            public void dispose() throws CacheException {
                status = Status.STATUS_SHUTDOWN;
                log.debug("Dispose the listener factory");
            }

            @Override
            public void notifyCacheAdded(String cacheName) {
                log.debug("The cache {} is added",cacheName);
            }

            @Override
            public void notifyCacheRemoved(String cacheName) {
                log.debug("The cache {} is removed",cacheName);
            }
        };
    }
}
