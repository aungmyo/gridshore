package nl.gridshore.newsfeed.web.news;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Content;
import com.sun.syndication.feed.rss.Item;
import nl.gridshore.newsfeed.domain.Comment;
import nl.gridshore.newsfeed.domain.NewsItem;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jettro Coenradie
 */
@Component("commentsRssFeed")
public class CommentsRssFeedView extends AbstractRssFeedView {

    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Channel feed, HttpServletRequest request) {
        NewsItem newsItem = (NewsItem)model.get("newsItem");
        feed.setTitle("Gridshore comments for : " +  newsItem.title());
        feed.setDescription("All comments from the gridshore news item : " + newsItem.getIntroduction());
        feed.setLink("http://localhost:8080/news/"+newsItem.getId());
    }

    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        NewsItem newsItem = (NewsItem)model.get("newsItem");
        List<Comment> comments = newsItem.comments();

        List<Item> items = new ArrayList<Item>();
        for (Comment comment : comments) {
            Item item = new Item();
            item.setAuthor(comment.getCommenter());
            Content content = new Content();
            content.setType(Content.HTML);
            content.setValue("<p>" + comment.content() + "</p>");
            item.setContent(content);
            item.setTitle("comment created on : " + comment.dateCreated());

            items.add(item);
        }
        return items;
    }
}
