package nl.gridshore.samples.raffle.dao.jpa;

import nl.gridshore.samples.raffle.dao.PriceDao;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 12, 2008
 * Time: 8:46:46 AM
 * Test case for the PriceDao
 */
public abstract class PriceDaoJpaTest extends BaseTestDaoJpa {
    private static final long PRICE_ID_WITH_WINNERS = 1L;

    private PriceDao priceDao;

    public void setPriceDao(PriceDao priceDao) {
        this.priceDao = priceDao;
    }


}
