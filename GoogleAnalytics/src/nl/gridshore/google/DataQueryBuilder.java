package nl.gridshore.google;

import com.google.gdata.client.analytics.DataQuery;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * This a builder for the <strong>com.google.gdata.client.analytics.DataQuery</strong> objects. It makes use of
 * a fluent interface to set all sorts of properties for the actual DataQuery. You can also ask for validity of the
 * configured builder.
 *
 * Validation rules:
 * - A maximum of 7 Dimensions
 * - A maximum of 10 Dimensions
 *
 * Not all combinations of Dimensions and Metrics are allowed. It is a pretty complex chart, check all valid
 * combinations on the following website:
 * http://code.google.com/apis/analytics/docs/gdata/gdataReferenceDimensionsMetrics.html
 * 
 * @author Jettro Coenradie
 */
public class DataQueryBuilder {
    public static final String DATA_URL = "https://www.google.com/analytics/feeds/data";
    private static Logger log = Logger.getLogger(DataQueryBuilder.class.getName());

    private URL baseUrl;
    private String startDate;
    private String endDate;
    private List<Dimension> dimensions;
    private List<Metric> metrics;
    private List<String> ids;
    private String sorting;
    private String filters;

    /**
     * Constructor that is not to be used from an outside class
     *
     * @param baseUrl Url object used to create the DataQuery
     * @param ids String vararch that contains the ids of the profiles that are used in the DataQuery object
     */
    private DataQueryBuilder(URL baseUrl, String... ids) {
        this.baseUrl = baseUrl;
        dimensions = new ArrayList<Dimension>();
        metrics = new ArrayList<Metric>();
        this.ids = Arrays.asList(ids);
        sorting = "";
    }

    /**
     * Factory method for creating a DataQueryBuilder object using the provided Profile objects
     * @param profiles Profile vararch containing the profiles to use in the query
     * @return DataQueryObject as instantiated by this factory method
     */
    public static DataQueryBuilder newBuilder(Profile... profiles) {
        if (profiles == null || profiles.length == 0) {
            log.warning("The provided profiles collection is empty which is not valid");
            throw new DataQueryBuilderException("Empty array of profiles is provided");
        }
        String[] ids = new String[profiles.length];
        for(int i = 0; i < profiles.length; i++) {
            ids[i] = profiles[i].getUniqueId();
        }
        return newBuilder(ids);
    }

    /**
     * Factory method for creating a DataQueryBuilder object using the provided ids of profiles
     * @param ids String vararch containing the ids to use in the query
     * @return DataQueryObject as instantiated by this factory method
     */
    public static DataQueryBuilder newBuilder(String... ids) {
        if (ids == null || ids.length == 0) {
            log.warning("The provided ids collection is empty which is not valid");
            throw new DataQueryBuilderException("Empty array of ids is provided");
        }
        for(String id : ids) {
            if ("".equals(id)) {
                throw new DataQueryBuilderException("An empty Id is provided, this is not allowed");
            }
        }
        return new DataQueryBuilder(createUrl(DATA_URL),ids);
    }

    /**
     * Creates the DataQuery instance from the provided parameters of the builder object
     * @return DataQuery object as instantiated using all proivded data
     */
    public DataQuery create() {
        DataQuery dataQuery = new DataQuery(baseUrl);
        dataQuery.setStartDate(startDate);
        dataQuery.setEndDate(endDate);
        dataQuery.setDimensions(getDimensions());
        dataQuery.setMetrics(getMetrics());
        dataQuery.setIds(getIds());
        dataQuery.setSort(getSorting());
        dataQuery.setFilters(filters);

        return dataQuery;
    }

    public DataQueryBuilder startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public DataQueryBuilder endDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public DataQueryBuilder dimension(Dimension... newDimensions) {
        dimensions.addAll(Arrays.asList(newDimensions));
        return this;
    }

    public DataQueryBuilder metric(Metric... newMetrics) {
        metrics.addAll(Arrays.asList(newMetrics));
        return this;
    }

    public DataQueryBuilder sortAsc(Metric metric) {
        this.sorting += metric + ",";
        return this;
    }

    public DataQueryBuilder sortDesc(Metric metric) {
        this.sorting += "-" + metric + ",";
        return this;
    }

    public DataQueryBuilder sortAsc(Dimension dimension) {
        this.sorting += dimension + ",";
        return this;
    }

    public DataQueryBuilder sortDesc(Dimension dimension) {
        this.sorting += "-" + dimension + ",";
        return this;
    }

    public DataQueryBuilder filters(String filters) {
        this.filters = filters;
        return this;
    }

    private String getIds() {
        return createStringFromList(ids);
    }

    private String getDimensions() {
        return createStringFromList(dimensions);
    }

    private String getMetrics() {
        return createStringFromList(metrics);
    }

    private <E> String createStringFromList(List<E> items) {
        if (items.isEmpty()) {
            return null;
        }
        StringBuffer sb = new StringBuffer(50);
        for (E item : items) {
            if (!item.toString().startsWith("ga:")) {
                sb.append("ga:");
            }
            sb.append(item)
                    .append(",");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private String getSorting() {
        if (sorting == null || "".equals(sorting)) {
            return null;
        }
        return sorting.substring(0,sorting.length()-1);
    }

    /*default*/
    static URL createUrl(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            log.throwing(DataQueryBuilder.class.getName(), "createUrl", e);
            throw new DataQueryBuilderException("Provided URL is not valid", e);
        }
    }
}
