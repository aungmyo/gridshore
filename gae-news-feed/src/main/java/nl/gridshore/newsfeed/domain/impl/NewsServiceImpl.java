package nl.gridshore.newsfeed.domain.impl;

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


    public List<NewsItem> listAllNewsItems() {
        return newsItemRepository.listAllNewsItems();
    }

    public void createNewsItem(String author, String title, String introduction, String item) {
        NewsItem newsItem = new NewsItem(author,title, introduction, item);
        newsItemRepository.persist(newsItem);
    }
}
