package nl.gridshore.newsfeed.web.news;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Content;
import com.sun.syndication.feed.rss.Item;
import nl.gridshore.newsfeed.domain.NewsItem;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jettro Coenradie
 */
@Component("newsRssFeed")
public class NewsRssFeedView extends AbstractRssFeedView {

    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Channel feed, HttpServletRequest request) {
        feed.setTitle("Gridshore news items");
        feed.setDescription("All news items from the gridshore source");
        feed.setLink("http://localhost:8080/news");
    }

    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        @SuppressWarnings({"unchecked"})
        List<NewsItem> newsItems = (List<NewsItem>) model.get("newsItems");
        List<Item> items = new ArrayList<Item>();
        for (NewsItem newsItem : newsItems) {
            Item item = new Item();
            item.setAuthor(newsItem.metaData().author());
            Content content = new Content();
            content.setType(Content.HTML);
            content.setValue("<p>" + newsItem.introduction() + "</p><p>" + newsItem.item() + "</p>");
            item.setContent(content);
            item.setTitle(newsItem.title());

            items.add(item);
        }
        return items;
    }
}
