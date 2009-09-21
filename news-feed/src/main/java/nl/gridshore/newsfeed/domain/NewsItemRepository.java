package nl.gridshore.newsfeed.domain;

import java.util.List;

/**
 * Repository implementation for news Items
 *
 * @author Jettro Coenradie
 */
public interface NewsItemRepository {
    void addNewsItem(NewsItem newsItem);
    NewsItem obtainNewsItemById(long id);
    List<NewsItem> listAllNewsItems();
    List<NewsItem> listNewsItemsLatest(int maxNumberOfItemsToReturn);
}
