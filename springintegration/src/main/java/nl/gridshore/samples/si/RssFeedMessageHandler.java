package nl.gridshore.samples.si;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.core.Message;

import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.synd.SyndFeed;
import nl.gridshore.samples.si.domain.NewsItem;

/**
 * Created by IntelliJ IDEA.
 * User: jettrocoenradie
 * Date: Mar 28, 2009
 * Time: 9:49:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class RssFeedMessageHandler {
    private static Logger logger = LoggerFactory.getLogger(RssFeedMessageHandler.class);

    public void handleMessage(Message<List<NewsItem>> message) {
        logger.debug("At {} I received a message with feedid {} and payload {}",new String[] {
                new Date(message.getHeaders().getTimestamp()).toString(),
                message.getHeaders().get("feedid",String.class),
                message.getPayload().toString()});
    }

}
