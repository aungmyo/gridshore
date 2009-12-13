package nl.gridshore.newsfeed.domainservice;

import nl.gridshore.newsfeed.domain.NewsItem;
import nl.gridshore.newsfeed.domain.NewsItemRepository;

import java.util.List;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
    public void createNewsItem(String author, Date publicationDate, String title, String introduction, String item) {
        NewsItem newsItem = new NewsItem(author,publicationDate, title, introduction, item);
        newsItemRepository.addNewsItem(newsItem);
    }

    @Override
    public NewsItem obtainNewsItem(Long newsId) {
        return newsItemRepository.obtainNewsItemById(newsId);
    }

    @Override
    public void addComment(Long newsItemId, String commenter, String content) {
        NewsItem newsItem = newsItemRepository.obtainNewsItemById(newsItemId);
        newsItem.addComment(commenter,content);
    }
}
