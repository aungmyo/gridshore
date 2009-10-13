package nl.gridshore.newsfeed.domain;

import nl.gridshore.newsfeed.web.NewsItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Jettro Coenradie
 */
public interface NewsService {
    @Transactional
    List<NewsItem> listAllNewsItems();

    @Transactional
    void createNewsItem(String nickName, String userId, String email,String title, String introduction, String item);

    @Transactional
    void discardNewsItem(long newsItemId);

    @Transactional
    void changeNewsItem(long id,String nickName, String userId, String email, String title, String introduction, String item);

    @Transactional
    NewsItem obtainNewsItem(long id);
}
