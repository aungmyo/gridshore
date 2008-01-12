package nl.gridshore.samples.raffle.dao.jpa;

import nl.gridshore.samples.raffle.dao.PrizeDao;
import nl.gridshore.samples.raffle.domain.Prize;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 28, 2007
 * Time: 8:23:39 PM
 * Prize data access component
 */
public class PrizeDaoJpa extends BaseDaoJpa<Prize> implements PrizeDao {
    public PrizeDaoJpa() {
        super(Prize.class, "Prize");
    }

    public List<Prize> loadByFilter(Prize entityFilter) {
        throw new UnsupportedOperationException();
    }

    public Prize loadByExample(Prize entity) {
        throw new UnsupportedOperationException();
    }
}
