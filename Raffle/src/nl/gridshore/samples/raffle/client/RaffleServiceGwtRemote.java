package nl.gridshore.samples.raffle.web.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 10-okt-2007
 * Time: 23:05:09
 * Remote interface for the Raffle service
 */
public interface RaffleServiceGwtRemote extends RemoteService {
    /**
     * Returns a random name as a string from the storage
     *
     * @return String containing a name
     */
    String getRandomName(String priceDesc);

    /**
     * Return all available names
     *
     * @return ArrayList of names
     * @gwt.typeArgs <java.lang.String>
     */
    ArrayList getAllNames();

    /**
     * Stores the provided name
     *
     * @param name String with the name to store
     */
    void storeName(String name);

    ArrayList getAllWinners();

    /**
     * Utility/Convenience class.
     * Use RaffleServiceGwtRemote.App.getInstance() to access static instance of RaffleServiceGwtRemoteAsync
     */
    public static class App {
        private static nl.gridshore.samples.raffle.web.gwt.client.RaffleServiceGwtRemoteAsync ourInstance = null;

        public static synchronized RaffleServiceGwtRemoteAsync getInstance() {
            if (ourInstance == null) {
                ourInstance = (RaffleServiceGwtRemoteAsync) GWT.create(RaffleServiceGwtRemote.class);
                ((ServiceDefTarget) ourInstance).setServiceEntryPoint(GWT.getModuleBaseURL() + "../raffleservice.rpc");
            }
            return ourInstance;
        }
    }
}
