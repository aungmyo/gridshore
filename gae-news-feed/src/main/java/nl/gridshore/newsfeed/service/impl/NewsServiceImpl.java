package nl.gridshore.newsfeed.service.impl;

import nl.gridshore.newsfeed.domain.Author;
import nl.gridshore.newsfeed.domain.NewsItem;
import nl.gridshore.newsfeed.domain.NewsItemRepository;
import nl.gridshore.newsfeed.service.NewsService;
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
        // http://groups.google.com/group/google-appengine-java/browse_thread/thread/4c896fd7db57bfc3/18c31c2c4b436479
        for(NewsItem newsItem : newsItems) {
            newsItem.getAuthor().getNickName();
        }
        return newsItems;
    }

    @Override
    public void createNewsItem(Author author, String title, String introduction, String item, long imageId) {
        NewsItem newsItem = new NewsItem(author, title, introduction, item, imageId);
        newsItemRepository.persist(newsItem);
    }

    @Override
    public void createNewsItem(Author author, String title, String introduction, String item) {
        NewsItem newsItem = new NewsItem(author, title, introduction, item);
        newsItemRepository.persist(newsItem);
    }

    @Override
    public void changeNewsItem(long id, Author author, String title, String introduction, String item) {
        doChangeNewsItem(id, author,title, introduction, item);
    }


    @Override
    public NewsItem obtainNewsItem(long id) {
        NewsItem newsItem = newsItemRepository.obtainNewsItemById(id);
        // Next line only needed for google app engine and detaching embedded objects, see listAllNewsItems
        newsItem.getAuthor().getNickName();
        return newsItem;
    }

    @Override
    public void discardNewsItem(long newsItemId) {
        NewsItem newsItem = newsItemRepository.obtainNewsItemById(newsItemId);
        newsItemRepository.remove(newsItem);
    }

    @Override
    public void changeNewsItem(long id, String title, String introduction, String item) {
        doChangeNewsItem(id, null,title, introduction, item);
    }

    private void doChangeNewsItem(long id, Author author, String title, String introduction, String item) {
        NewsItem currentNewsItem = newsItemRepository.obtainNewsItemById(id);
        currentNewsItem.setIntroduction(introduction);
        currentNewsItem.setItem(item);
        currentNewsItem.setTitle(title);
        if (author != null) {
            currentNewsItem.setAuthor(author);
        }
    }

}
