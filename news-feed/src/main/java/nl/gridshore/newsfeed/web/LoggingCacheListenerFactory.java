package nl.gridshore.newsfeed.web;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;
import net.sf.ehcache.event.CacheManagerEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author Jettro Coenradie
 */
public class LoggingCacheListenerFactory extends CacheEventListenerFactory {
    private static final Logger log = LoggerFactory.getLogger(LoggingCacheListenerFactory.class);

    @Override
    public CacheEventListener createCacheEventListener(Properties properties) {

        return new CacheEventListener() {

            @Override
            public Object clone() throws CloneNotSupportedException {
                log.debug("Clone obtained");
                return super.clone();
            }

            @Override
            public void notifyElementRemoved(Ehcache cache, Element element) throws CacheException {
                log.debug("Element removed from the cache : {}",element.getObjectKey());
            }

            @Override
            public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
                log.debug("Element put into the cache : {}",element.getObjectKey());
            }

            @Override
            public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {
                log.debug("Element updated in the cache : {}",element.getObjectKey());
            }

            @Override
            public void notifyElementExpired(Ehcache cache, Element element) {
                log.debug("Element expired in the cache : {}",element.getObjectKey());
            }

            @Override
            public void notifyElementEvicted(Ehcache cache, Element element) {
                log.debug("Element evicted from the cache : {}",element.getObjectKey());
            }

            @Override
            public void notifyRemoveAll(Ehcache cache) {
                log.debug("Remove all elements from the cache");
            }

            @Override
            public void dispose() {
                log.debug("Dispose the listener");
            }
        };
    }
}
