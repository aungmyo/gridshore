package nl.gridshore.google;

import com.google.gdata.client.analytics.DataQuery;
import com.google.gdata.data.analytics.DataEntry;
import com.google.gdata.data.analytics.DataFeed;

import java.util.List;

/**
 * Example application using the AnalyticsServiceWrapper and the DataQueryBuilder
 *
 * @author Jettro Coenradie
 */
public class Application {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Not enough arguments supplied:");
            System.out.println("arg 1 : username");
            System.out.println("arg 2 : password");
            return;
        }

        String username = args[0];
        String password = args[1];

        AnalyticsServiceWrapper serviceWrapper = new AnalyticsServiceWrapper(username, password);

        List<Profile> profiles = serviceWrapper.obtainProfiles();
        printProfileInformation(profiles);

        DataFeed feed = serviceWrapper.obtainQueryResults(createQuery(obtainGridshoreProfile(profiles)));
        printQueryResults(feed);

    }

    private static Profile obtainGridshoreProfile(List<Profile> profiles) {
        for (Profile profile : profiles) {
            if (profile.getTitle().contains("gridshore")) {
                return profile;
            }
        }
        throw new RuntimeException("Gridshore profile is not found");
    }

    private static void printProfileInformation(List<Profile> profiles) {
        for (Profile profile : profiles) {
            System.out.println("Title : " + profile.getTitle() + " key : " + profile.getUniqueId());
        }
    }

    private static void printQueryResults(DataFeed feed) {
        for (DataEntry entry : feed.getEntries()) {
            System.out.println(
                    "Dag: " + entry.stringValueOf(Dimension.day.toString()) +
                            " " + entry.stringValueOf(Metric.visits.toString()) +
                            " " + entry.stringValueOf(Metric.visitors.toString())
            );

        }
    }

    public static DataQuery createQuery(Profile profile) {
        return DataQueryBuilder.newBuilder(profile)
                .startDate("2009-05-01")
                .endDate("2009-05-31")
                .dimension(Dimension.day)
                .metric(Metric.visits,Metric.visitors)
                .filters("ga:visits>=500")
                .create();
    }

}
