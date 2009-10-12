package nl.gridshore.newsfeed.persistence;

import nl.gridshore.newsfeed.domain.NewsItem;
import nl.gridshore.newsfeed.domain.NewsItemRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Jettro Coenradie
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class JpaNewsItemRepository implements NewsItemRepository {
    private EntityManager entityManager;

    @Override
    public void persist(NewsItem newsItem) {
        entityManager.persist(newsItem);
    }

    @Override
    public void remove(NewsItem newsItem) {
        entityManager.remove(newsItem);
    }

    @Override
    public NewsItem obtainNewsItemById(long id) {
        return entityManager.find(NewsItem.class, id);
    }

    @Override
    public List<NewsItem> listAllNewsItems() {
        List resultList = entityManager.createQuery("select ni from NewsItem ni").getResultList();
        resultList.size();
        return resultList;
    }


    /* setters */
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
