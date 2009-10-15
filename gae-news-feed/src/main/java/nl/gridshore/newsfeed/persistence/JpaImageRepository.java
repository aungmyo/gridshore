package nl.gridshore.newsfeed.persistence;

import nl.gridshore.newsfeed.domain.Image;
import nl.gridshore.newsfeed.domain.ImageRepository;
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
public class JpaImageRepository implements ImageRepository {
    private EntityManager entityManager;


    @Override
    public long persist(Image image) {
        entityManager.persist(image);
        entityManager.flush();
        return image.getId();
    }

    @Override
    public Image obtainImageById(long id) {
        return entityManager.find(Image.class,id);
    }

    @Override
    public List<Image> listAllImages() {
        List resultList = entityManager.createQuery("select i from Image i").getResultList();
        resultList.size();
        //noinspection unchecked
        return resultList;
    }

    /* setters */
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
