package nl.gridshore.samples.raffle.web.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 10-okt-2007
 * Time: 23:05:09
 * To change this template use File | Settings | File Templates.
 */
public interface RaffleServiceGwtRemoteAsync {

    /**
     * Returns a random name as a string from the storage
     *
     * @return String containing a name
     */
    void getRandomName(String prizeDesc, AsyncCallback async);

    /**
     * Return all available names
     *
     * @return ArrayList of names
     * @gwt.typeArgs <java.lang.String>
     */
    void getAllNames(AsyncCallback async);

    /**
     * Stores the provided name
     *
     * @param name String with the name to store
     */
    void storeName(String name, AsyncCallback async);

    void getAllWinners(AsyncCallback async);
}
