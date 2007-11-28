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
        List<Raffle> raffles = query.getResultList();
        for (Raffle raffle : raffles) {
            raffle.getPrices().size();
        }
        return raffles;
    }

    public Raffle loadByExample(Raffle entity) {
        throw new UnsupportedOperationException();
    }

    public Raffle loadById(Long entityId) {
        Raffle raffle = super.loadById(entityId);
        raffle.getPrices().size();
        return raffle;
    }

    public List<Raffle> loadAll() {
        List<Raffle> raffles = super.loadAll();
        for (Raffle raffle : raffles) {
            raffle.getPrices().size();
        }
        return raffles;
    }
}
