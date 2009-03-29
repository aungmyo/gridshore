package nl.gridshore.samples.si.helper;

import com.sun.syndication.fetcher.FetcherListener;
import com.sun.syndication.fetcher.FetcherEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: jettrocoenradie
 * Date: Mar 28, 2009
 * Time: 9:23:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class FetcherEventListenerImpl implements FetcherListener {
    private static Logger logger = LoggerFactory.getLogger(FetcherEventListenerImpl.class);

    public void fetcherEvent(FetcherEvent event) {
        String eventType = event.getEventType();
        if (FetcherEvent.EVENT_TYPE_FEED_POLLED.equals(eventType)) {
            logger.info("EVENT: Feed Polled. URL = {}", event.getUrlString());
        } else if (FetcherEvent.EVENT_TYPE_FEED_RETRIEVED.equals(eventType)) {
            logger.info("EVENT: Feed Retrieved. URL = {}", event.getUrlString());
        } else if (FetcherEvent.EVENT_TYPE_FEED_UNCHANGED.equals(eventType)) {
            logger.info("EVENT: Feed Unchanged. URL = {}", event.getUrlString());
        }

    }
}
