package nl.gridshore.newsfeed.domainservice;

import nl.gridshore.newsfeed.domain.NewsItem;
import nl.gridshore.newsfeed.domain.NewsItemRepository;

import java.util.List;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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

    public void createNewsItem(String author, Date publicationDate, String title, String introduction, String item) {
        NewsItem newsItem = new NewsItem(author,publicationDate, title, introduction, item);
        newsItemRepository.addNewsItem(newsItem);
    }
}
