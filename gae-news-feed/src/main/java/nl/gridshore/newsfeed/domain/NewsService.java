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
    void createNewsItem(String author, String title, String introduction, String item);

}
