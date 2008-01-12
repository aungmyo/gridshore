package nl.gridshore.samples.raffle.dao.jpa;

import org.springframework.test.jpa.AbstractJpaTests;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 12, 2008
 * Time: 8:48:25 AM
 * Base class to use for our dao test classes
 */
public class BaseTestDaoJpa extends AbstractJpaTests {
    protected void onSetUp() throws Exception {
        super.onSetUp();
    }

    protected String[] getConfigLocations() {
        return new String[]{"classpath:test-dao-spring.xml", "classpath:dao-config.xml"};
    }

}
