package nl.gridshore.newsfeed.service;

import nl.gridshore.newsfeed.domain.Author;
import nl.gridshore.newsfeed.domain.NewsItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service interface for all {@code NewsItem} related actions
 *
 * @author Jettro Coenradie
 */
public interface NewsService {
    /**
     * Returns all available news items in a list
     *
     * @return List containing all {@code NewsItem} objects
     */
    @Transactional
    List<NewsItem> listAllNewsItems();

    /**
     * Create a new NewsItem and store it in the datastore
     *
     * @param author Author containing the information about the {@code Author} of the NewsIem
     * @param title String containing the title of the NewsItem
     * @param introduction String containing the introduction of the news item
     * @param item String containing the actual news item
     * @param imageId long representing the if of the Image for this news item.
     */
    @Transactional
    void createNewsItem(Author author, String title, String introduction, String item, long imageId);

    /**
     * Create a new NewsItem and store it in the datastore
     *
     * @param author Author containing the information about the {@code Author} of the NewsIem
     * @param title String containing the title of the NewsItem
     * @param introduction String containing the introduction of the news item
     * @param item String containing the actual news item
     */
    @Transactional
    void createNewsItem(Author author, String title, String introduction, String item);

    /**
     * Discards the NewsItem with the provided id from the repository
     *
     * @param newsItemId long representing the id of the NewsItem to discard
     */
    @Transactional
    void discardNewsItem(long newsItemId);

    /**
     * Change the news item with the provided data
     *
     * @param id long representing the id of the NewsItem to change
     * @param author Author containing the information about the {@code Author} of the NewsIem
     * @param title String containing the title of the NewsItem
     * @param introduction String containing the introduction of the news item
     * @param item String containing the actual news item
     */
    @Transactional
    void changeNewsItem(long id, Author author, String title, String introduction, String item);

    /**
     * Change the news item with the provided data
     *
     * @param id long representing the id of the NewsItem to change
     * @param title String containing the title of the NewsItem
     * @param introduction String containing the introduction of the news item
     * @param item String containing the actual news item
     */
    @Transactional
    void changeNewsItem(long id, String title, String introduction, String item);

    /**
     * Obtain a news item with the provided id
     *
     * @param id long representing the id of the NewsItem to obtain
     * @return NewsItem with the provided id
     */
    @Transactional
    NewsItem obtainNewsItem(long id);
}
