package nl.gridshore.newsfeed.persistence;

import nl.gridshore.newsfeed.domain.NewsItemRepository;
import nl.gridshore.newsfeed.domain.NewsItem;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

/**
 * jpa implementation of the {@link nl.gridshore.newsfeed.domain.NewsItemRepository}
 *
 * @author Jettro Coenradie
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class JpaNewsItemRepository implements NewsItemRepository {
    private static final Logger logger = LoggerFactory.getLogger(JpaNewsItemRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void addNewsItem(NewsItem newsItem) {
        entityManager.persist(newsItem);
    }

    public NewsItem obtainNewsItemById(long id) {
        NewsItem newsItem = entityManager.find(NewsItem.class, id);
        if (newsItem == null) {
            logger.warn("Could not find news item with id {}",id);
            return null;
        }
        newsItem.comments().size();
        return newsItem;
    }

    public List<NewsItem> listAllNewsItems() {
        //noinspection unchecked
        return entityManager.createQuery("select ni from NewsItem ni").getResultList();
    }

    public List<NewsItem> listNewsItemsLatest(int maxNumberOfItemsToReturn) {
        //noinspection unchecked
        return entityManager.createQuery("select ni from NewsItem ni").setMaxResults(maxNumberOfItemsToReturn).getResultList();
    }
}
