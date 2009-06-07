package nl.gridshore.google;

/**
 * Enumeration for all dimensions
 * <p/>
 * The primary data keys from which Analytics reports are constructed. Like metrics, dimensions are also categorized
 * by type. For example, ga:pageTitle and ga:page are two Content report dimensions and ga:browser, ga:city, and
 * ga:pageDepth are two visitor dimensions.
 * <p/>
 * Your site's metrics can be segmented by dimension. For example, while you can ask for the total number of pageviews
 * to your site, it might be more interesting to ask for the number of pageviews segmented by browser. In this case,
 * you'll see the number of pageviews from Firefox, Internet Explorer, Chrome, and so forth.
 * <p/>
 * http://code.google.com/apis/analytics/docs/gdata/gdataReferenceDimensionsMetrics.html
 */
public enum Dimension {
    // visitor
    browser, browserVersion, city, connectionSpeed, continent, countOfVisits, country, date, day, daysSinceLastVisit, flashVersion, hostname, hour, javaEnabled, language, latitude, longitude, month, networkDomain, networkLocation, pageDepth, operatingSystem, operatingSystemVersion, region, screenColors, screenResolution, subContinent, userDefinedValue, visitorType, week, year

    // campaign
    , adContent, adGroup, adSlot, adSlotPosition, campaign, keyword, medium, referralPath, source

    //content
    , exitPagePath, landingPagePath, pagePath, pageTitle

    //Ecommerce
    , affiliation, daysToTransaction, productCategory, productName, productSku, transactionId

    //Internal search
    , searchCategory, searchDestinationPage, searchKeyword, searchKeywordRefinement, searchStartPage, searchUsed
    ;

    @Override
    public String toString() {
        return "ga:" + super.toString();
    }

}
