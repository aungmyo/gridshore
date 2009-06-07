package nl.gridshore.google;

/**
 * Enumeration for all supported metrics
 * <p/>
 * The aggregated statistics for user activity in a profile, categorized by type. Examples of metrics include
 * ga:clicks or ga:pageviews. When queried by themselves, metrics provide an aggregate measure of user activity
 * for your profile, such as overall page views or bounce rate. However, when paired with dimensions, they provide
 * information in the context of the dimension. For example, when pageviews are combined with ga:countryOrTerritory,
 * you see how many pageviews come from each country.
 * <p/>
 * http://code.google.com/apis/analytics/docs/gdata/gdataReferenceDimensionsMetrics.html
 */
public enum Metric {
    // Visitor
    bounces, entrances, exits, newVisits, pageviews, timeOnPage, timeOnSite, visitors, visits

    // Campaign
    , adCost, adClicks, CPC, CPM, CTR, impressions

    // Content
    , uniquePageviews

    // Ecommerce
    , itemQuantity, itemRevenue, transactionRevenue, transactions, transactionShipping, transactionTax, uniquePurchases

    // Internal Search
    , searchDepth, searchDuration, searchExits, searchRefinements, searchUniques, searchVisits

    // Goals
    , goal1Completions, goal2Completions, goal3Completions, goal4Completions, goalCompletionsAll, goal1Starts, goal2Starts, goal3Starts, goal4Starts, goalStartsAll, goal1Value, goal2Value, goal3Value, goal4Value, goalValueAll
    ;


    @Override
    public String toString() {
        return "ga:" + super.toString();
    }
}
