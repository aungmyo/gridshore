package nl.gridshore.samples.si;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.FetcherException;
import com.sun.syndication.fetcher.FetcherListener;
import com.sun.syndication.fetcher.impl.FeedFetcherCache;
import com.sun.syndication.fetcher.impl.HashMapFeedInfoCache;
import com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;
import com.sun.syndication.io.FeedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.integration.core.Message;
import org.springframework.integration.message.MessageBuilder;
import org.springframework.integration.message.MessageSource;

import java.io.IOException;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: jettrocoenradie
 * Date: Mar 28, 2009
 * Time: 9:41:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class RssReader implements MessageSource, InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(RssReader.class);
    private FeedFetcherCache feedInfoCache;
    private FeedFetcher feedFetcher;
    private FetcherListener fetcherListener;

    public Message<SyndFeed> receive() {
        logger.debug("readRssFeed method is called");
        SyndFeed feed = obtainFeedItems();
        return MessageBuilder.withPayload(feed)
                .setHeader("feedid", "gridshore").build();

    }

    private SyndFeed obtainFeedItems() {
        SyndFeed feed = null;
        try {
            feed = feedFetcher.retrieveFeed(new URL("http://www.gridshore.nl/feed/"));
        } catch (IOException e) {
            logger.error("IO Problem while retrieving feed", e);
        } catch (FeedException e) {
            logger.error("Feed Problem while retrieving feed", e);
        } catch (FetcherException e) {
            logger.error("Fetcher Problem while retrieving feed", e);
        }
        return feed;
    }

    public void afterPropertiesSet() throws Exception {
        feedInfoCache = HashMapFeedInfoCache.getInstance();
        feedFetcher = new HttpURLFeedFetcher(feedInfoCache);
        if (fetcherListener != null) {
            feedFetcher.addFetcherEventListener(fetcherListener);
        }
    }

    public void setFetcherListener(FetcherListener fetcherListener) {
        this.fetcherListener = fetcherListener;
    }
}
