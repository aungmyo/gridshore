package nl.gridshore.samples.raffle.dao.jpa;

import nl.gridshore.samples.raffle.dao.WinnerDao;
import nl.gridshore.samples.raffle.domain.Winner;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 12, 2008
 * Time: 8:57:52 AM
 * Implementation for accessing winner data using jpa
 */
public class WinnerDaoJpa extends BaseDaoJpa<Winner> implements WinnerDao {
    public WinnerDaoJpa() {
        super(Winner.class, "Winner");
    }

    public List<Winner> loadByFilter(Winner entityFilter) {
        throw new UnsupportedOperationException();
    }

    public Winner loadByExample(Winner entity) {
        throw new UnsupportedOperationException();
    }
}
