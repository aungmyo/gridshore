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
        return newsItemRepository.listAllNewsItems();
    }

    @Override
    public void createNewsItem(String nickName, String userId, String email, String title, String introduction, String item) {
        Author author = new Author(userId,nickName,email);
        NewsItem newsItem = new NewsItem(author, title, introduction, item);
        newsItemRepository.persist(newsItem);
    }

    @Override
    public void discardNewsItem(long newsItemId) {
        NewsItem newsItem = newsItemRepository.obtainNewsItemById(newsItemId);
        newsItemRepository.remove(newsItem);
    }

}
