package nl.gridshore.newsfeed.domain;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jettro Coenradie
 */
public interface NewsService {
    @Transactional
    List<NewsItem> listAllNewsItems();

    @Transactional
    void createNewsItem(Author author, String title, String introduction, String item, Long imageId);

    @Transactional
    void discardNewsItem(long newsItemId);

    @Transactional
    void changeNewsItem(long id, Author author, String title, String introduction, String item);

    @Transactional
    void changeNewsItem(long id, String title, String introduction, String item);

    @Transactional
    NewsItem obtainNewsItem(long id);
}
