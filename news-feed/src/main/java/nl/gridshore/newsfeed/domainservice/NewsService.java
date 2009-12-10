package nl.gridshore.newsfeed.domainservice;

import nl.gridshore.newsfeed.domain.NewsItem;

import java.util.List;
import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

/**
 * Domain service that provides services related to {@link nl.gridshore.newsfeed.domain.NewsItem}s.
 *
 * @author Jettro Coenradie
 */
public interface NewsService {
    @Transactional
    List<NewsItem> listAllNewsItems();
    
    @Transactional
    void createNewsItem(String author, Date publicationDate, String title, String introduction, String item);
}
