package nl.gridshore.newsfeed.domain;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jettro Coenradie
 */
public interface NewsItemRepository {
    void persist(NewsItem newsItem);

    NewsItem obtainNewsItemById(long id);

    List<NewsItem> listAllNewsItems();

    void remove(NewsItem newsItem);
}
