package nl.gridshore.samples.raffle.dao.jpa;

import nl.gridshore.samples.raffle.dao.PriceDao;
import nl.gridshore.samples.raffle.domain.Price;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Nov 28, 2007
 * Time: 8:23:39 PM
 * Price data access component
 */
public class PriceDaoJpa extends BaseDaoJpa<Price> implements PriceDao {
    public PriceDaoJpa() {
        super(Price.class, "Price");
    }

    public List<Price> loadByFilter(Price entityFilter) {
        throw new UnsupportedOperationException();
    }

    public Price loadByExample(Price entity) {
        throw new UnsupportedOperationException();
    }
}
