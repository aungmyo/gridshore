package nl.gridshore.newsfeed.domain;

import java.util.List;

/**
 * Repository object for all persistence related actions on {@code NewsItem}s
 *
 * @author Jettro Coenradie
 */
public interface NewsItemRepository {
    /**
     * Persist the provided NewsItem
     * @param newsItem NewsItem to be persisted
     */
    void persist(NewsItem newsItem);

    /**
     * Returns the NewsItem for the provided id
     * @param id long containing the id of the NewsItem to find
     * @return NewsItem found for the id.
     */
    NewsItem obtainNewsItemById(long id);

    /**
     * Returns all available NewsItem objects
     * @return List containing all news items
     */
    List<NewsItem> listAllNewsItems();

    /**
     * Removes a NewsItem from the datastore
     * @param newsItem NewsItem to be removed.
     */
    void remove(NewsItem newsItem);
}
