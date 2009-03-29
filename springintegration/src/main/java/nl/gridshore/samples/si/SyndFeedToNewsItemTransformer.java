package nl.gridshore.samples.si;

import org.springframework.integration.core.Message;
import org.springframework.integration.message.MessageBuilder;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import nl.gridshore.samples.si.domain.NewsItem;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndContent;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: jettrocoenradie
 * Date: Mar 29, 2009
 * Time: 8:38:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class SyndFeedToNewsItemTransformer {
    private Logger logger = LoggerFactory.getLogger(SyndFeedToNewsItemTransformer.class);

    public Message<List<NewsItem>> transform(Message<SyndFeed> syndFeedMessage) {
        logger.debug("Received a feed from the blog {}",syndFeedMessage.getPayload().getTitle());

        SyndFeed syndFeed = syndFeedMessage.getPayload();

        List<NewsItem> newsItems = new ArrayList<NewsItem>();
        List syndFeedItems = syndFeed.getEntries();
        for (Object syndFeedEntry:syndFeedItems) {
            SyndEntry syndEntry = (SyndEntry)syndFeedEntry;
            String title = syndEntry.getTitle();
            String author = syndEntry.getAuthor();
            String description = syndEntry.getDescription().getValue();
            SyndContent syndContent = (SyndContent)syndEntry.getContents().get(0);
            String content = syndContent.getValue();
            // a lot of other information is possible
            newsItems.add(new NewsItem(title,description,content));
        }
        Message<List<NewsItem>> newMessage = MessageBuilder.withPayload(newsItems)
                .copyHeaders(syndFeedMessage.getHeaders()).build();
        return newMessage;
    }
}
