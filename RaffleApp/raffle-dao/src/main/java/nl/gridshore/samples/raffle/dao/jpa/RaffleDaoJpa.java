package nl.gridshore.samples.raffle.dao.jpa;

import nl.gridshore.samples.raffle.dao.RaffleDao;
import nl.gridshore.samples.raffle.domain.Raffle;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 3-nov-2007
 * Time: 0:00:28
 * Hibernate Jpa implementation for the RaffleDao
 */
public class RaffleDaoJpa extends BaseDaoJpa<Raffle> implements RaffleDao {
    public RaffleDaoJpa() {
        super(Raffle.class, "Raffle");
    }

    public List<Raffle> loadByFilter(Raffle entityFilter) {
        Query query = getEntityManager().createQuery("select r from Raffle as r where r.title like :title");
        query.setParameter("title", "%" + entityFilter.getTitle() + "%");
        //noinspection unchecked
        return query.getResultList();
    }

    public Raffle loadByExample(Raffle entity) {
        throw new UnsupportedOperationException();
    }

}
