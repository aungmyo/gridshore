package nl.gridshore.google;

import com.google.gdata.client.analytics.AnalyticsService;
import com.google.gdata.client.analytics.DataQuery;
import com.google.gdata.data.analytics.AccountEntry;
import com.google.gdata.data.analytics.AccountFeed;
import com.google.gdata.data.analytics.DataFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Basic class for obtaining google analytics data. This wrapper contains an AnalyticsService. During contruction you
 * need to provide a username and a password. Than you can use the appropriate methods to obtain profile information
 * for the logged in user and execute quesries.
 *
 * @author Jettro Coenradie
 */
public class AnalyticsServiceWrapper {
    public static final String ACCOUNT_URL = "https://www.google.com/analytics/feeds/accounts/default";

    private static Logger log = Logger.getLogger(DataQueryBuilder.class.getName());

    private AnalyticsService analyticsService;

    /**
     * Default Constructor for the AnalyticsServiceWrapper. Expect exceptions when the username and password are not
     * valid.
     *
     * @param username String containing the username to use for the credentials
     * @param password String containing the password to use for the credentials
     */
    public AnalyticsServiceWrapper(String username, String password) {
        analyticsService = new AnalyticsService("gridshore-analytics");
        try {
            analyticsService.setUserCredentials(username, password);
        } catch (AuthenticationException e) {
            log.log(Level.SEVERE, "problem while setting username/password combination", e);
            throw new AnalyticsServiceException("Problem while registering with provided credentials");
        }
    }

    /**
     * Returns a list of profile objects that belong to the user of the provided credentials
     *
     * @return List of profile objects
     */
    public List<Profile> obtainProfiles() {
        List<Profile> profiles = new ArrayList<Profile>();
        AccountFeed accountFeed = null;
        try {
            accountFeed = analyticsService.getFeed(new URL(ACCOUNT_URL), AccountFeed.class);
        } catch (IOException e) {
            log.log(Level.WARNING, "Problem while obtaining account information", e);
            throw new AnalyticsServiceException("IO problem while ontaining account information", e);
        } catch (ServiceException e) {
            log.log(Level.WARNING, "Problem while obtaining account information", e);
            throw new AnalyticsServiceException("Service problem while ontaining account information", e);
        }
        for (AccountEntry accountEntry : accountFeed.getEntries()) {
            profiles.add(new Profile(accountEntry.getTitle().getPlainText(), accountEntry.getTableId().getValue()));
        }
        return profiles;
    }

    /**
     * Executes the provided DataQuery and returns the results
     *
     * @param query DataQuery to execute
     * @return DataFeed as the results of an exectured query.
     */
    public DataFeed obtainQueryResults(DataQuery query) {
        try {
            return analyticsService.getFeed(query, DataFeed.class);
        } catch (IOException e) {
            log.log(Level.WARNING, "IO problem while executing query", e);
            throw new AnalyticsServiceException("Problem while executing query", e);
        } catch (ServiceException e) {
            log.log(Level.WARNING, "Service problem while executing query", e);
            throw new AnalyticsServiceException("Problem while executing query", e);
        }

    }
}
