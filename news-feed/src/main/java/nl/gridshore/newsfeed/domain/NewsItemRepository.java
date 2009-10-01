package nl.gridshore.newsfeed.domain;

import org.springframework.orm.ObjectRetrievalFailureException;

import java.util.List;

/**
 * Repository implementation for news Items
 *
 * @author Jettro Coenradie
 */
public interface NewsItemRepository {
    /**
     * Add a new {@code NewsItem} to the repository
     * @param newsItem {@code NewsItem} to store
     */
    void addNewsItem(NewsItem newsItem);

    /**
     * Obtain a news item by id, throw an ObjectRetrievalFailureException if the id cannot be found
     * @param id long representing the id of the news item to look for
     * @return The found id
     * @throws org.springframework.orm.ObjectRetrievalFailureException if the NewsItem belonging to the provided id
     * cannot be found.
     */
    NewsItem obtainNewsItemById(long id) throws ObjectRetrievalFailureException;

    /**
     * Returns a list with all provided {@code NewsItem}
     * @return List containing all news items
     */
    List<NewsItem> listAllNewsItems();

    /**
     * Returns a maximum number of {@code NewsItem} objects
     * @param maxNumberOfItemsToReturn int representing the maximum amount of items to return
     * @return List containing {@code NewsItem} objects.                                                                                 
     */
    List<NewsItem> listNewsItemsLatest(int maxNumberOfItemsToReturn);
}
