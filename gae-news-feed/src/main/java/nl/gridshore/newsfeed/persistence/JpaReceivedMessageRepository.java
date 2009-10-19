package nl.gridshore.newsfeed.persistence;

import nl.gridshore.newsfeed.domain.ReceivedMessage;
import nl.gridshore.newsfeed.domain.ReceivedMessageRepository;
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
public class JpaReceivedMessageRepository implements ReceivedMessageRepository {
    private EntityManager entityManager;

    @Override
    public void persist(ReceivedMessage receivedMessage) {
        entityManager.persist(receivedMessage);
    }

    @Override
    public List<ReceivedMessage> listAllReceivedMessages() {
        List resultList = entityManager.createQuery("select rm from ReceivedMessage rm").getResultList();
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
