package nl.gridshore.newsfeed.domain.impl;

import nl.gridshore.newsfeed.domain.Author;
import nl.gridshore.newsfeed.domain.NewsItem;
import nl.gridshore.newsfeed.domain.NewsItemRepository;
import nl.gridshore.newsfeed.domain.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jettro Coenradie
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsItemRepository newsItemRepository;

    @Override
    public List<NewsItem> listAllNewsItems() {
        List<NewsItem> newsItems = newsItemRepository.listAllNewsItems();
        // The following lines are only done to satisfy google app engine. If not done we have problems with
        // detaching objects that are embedded (like our Author in NewsItem)
        // http://groups.google.com/group/google-appengine-java/browse_thread/thread/4c896fd7db57bfc3/18c31c2c4b436479?hl=en&lnk=gst&q=jpa+detach#18c31c2c4b436479
        for(NewsItem newsItem : newsItems) {
            newsItem.getAuthor().getNickName();
        }
        return newsItems;
    }

    @Override
    public void createNewsItem(String nickName, String userId, String email, String title, String introduction, String item) {
        Author author = new Author(userId,nickName,email);
        NewsItem newsItem = new NewsItem(author, title, introduction, item);
        newsItemRepository.persist(newsItem);
    }

    @Override
    public void changeNewsItem(long id,String nickName, String userId, String email, String title, String introduction, String item) {
        NewsItem currentNewsItem = newsItemRepository.obtainNewsItemById(id);
        currentNewsItem.setIntroduction(introduction);
        currentNewsItem.setItem(item);
        currentNewsItem.setTitle(title);
    }

    @Override
    public NewsItem obtainNewsItem(long id) {
        return newsItemRepository.obtainNewsItemById(id);
    }

    @Override
    public void discardNewsItem(long newsItemId) {
        NewsItem newsItem = newsItemRepository.obtainNewsItemById(newsItemId);
        newsItemRepository.remove(newsItem);
    }

}
