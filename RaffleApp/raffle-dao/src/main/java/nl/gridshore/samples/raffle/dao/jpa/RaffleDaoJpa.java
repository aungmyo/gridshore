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
public class RaffleDaoJpa extends BaseDaoJpa<Raffle> implements RaffleDao  {
    public RaffleDaoJpa() {
        super(Raffle.class);
    }

    public List<Raffle> loadAll() {
        Query query = getEntityManager().createQuery("from Raffle raffle");
        return query.getResultList();
    }
}
