package nl.gridshore.google;

import com.google.gdata.client.analytics.DataQuery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test case for the DataQueryBuilder class
 *
 * @author Jettro Coenradie
 */
public class DataQueryBuilderTest {
    private static final String BASE_ID = "100000";

    @Before
    public void initializeTest() {
        Logger logger = Logger.getLogger("nl.gridshore");
        logger.setLevel(Level.ALL);
    }

    /*---------- createNewBuilder ---------*/
    @Test
    public void createNewBuilder() throws Exception {
        DataQueryBuilder builder = DataQueryBuilder.newBuilder(BASE_ID);
        assertNotNull("The builder should not be null after creation", builder);
    }

    @Test(expected = DataQueryBuilderException.class)
    public void createNewBuilder_noId() throws Exception {
        DataQueryBuilder.newBuilder("");
    }

    @Test
    public void createNewBuilder_profile() {
        DataQuery query = DataQueryBuilder.newBuilder(new Profile("test", "10000")).create();
        assertEquals("ga:10000", query.getIds());
    }

    /*---------- create ---------*/
    @Test
    public void create() {
        DataQuery query = DataQueryBuilder.newBuilder(BASE_ID).create();
        URL queryUrl = query.getUrl();
        String url = queryUrl.getProtocol() + "://" + queryUrl.getHost() + queryUrl.getPath();
        assertEquals(DataQueryBuilder.DATA_URL, url);
    }

    @Test
    public void create_withPrefixGa() {
        DataQuery query = DataQueryBuilder.newBuilder("ga:" + BASE_ID).create();
        URL queryUrl = query.getUrl();
        String url = queryUrl.getProtocol() + "://" + queryUrl.getHost() + queryUrl.getPath();
        assertEquals(DataQueryBuilder.DATA_URL, url);
        assertEquals("ga:" + BASE_ID, query.getIds());
    }

    @Test
    public void create_withData() {
        DataQuery query = DataQueryBuilder.newBuilder(BASE_ID)
                .startDate("2009-01-01")
                .endDate("2009-01-31")
                .dimension(Dimension.day, Dimension.country)
                .metric(Metric.pageviews, Metric.visits)
                .sortAsc(Metric.pageviews)
                .sortDesc(Dimension.country)
                .filters("ga:pageviews>=10")
                .create();

        assertEquals("ids not correct", "ga:" + BASE_ID, query.getIds());
        assertEquals("endDate not correct", "2009-01-31", query.getEndDate());
        assertEquals("startDate not correct", "2009-01-01", query.getStartDate());
        assertEquals("dimensions not correct", "ga:day,ga:country", query.getDimensions());
        assertEquals("metrics not correct", "ga:pageviews,ga:visits", query.getMetrics());
        assertEquals("sorting not correct", "ga:pageviews,-ga:country", query.getSort());
        assertEquals("filtering not correct", "ga:pageviews>=10", query.getFilters());
    }

}
