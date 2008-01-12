package nl.gridshore.samples.raffle.dao.jpa;

import nl.gridshore.samples.raffle.dao.PrizeDao;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 12, 2008
 * Time: 8:46:46 AM
 * Test case for the PrizeDao
 */
public abstract class PrizeDaoJpaTest extends BaseTestDaoJpa {

    private PrizeDao prizeDao;

    public void setPrizeDao(PrizeDao prizeDao) {
        this.prizeDao = prizeDao;
    }


}
