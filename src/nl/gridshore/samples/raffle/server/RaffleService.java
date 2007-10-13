package nl.gridshore.samples.raffle.server;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 10-okt-2007
 * Time: 20:33:47
 * Service to expose a raffle system. The system manages items on which we can perform some actions.
 * One of the actions is to obtain a random item.
 */
public interface RaffleService<T> {
    /**
     * Add an item to the managed items.
     * @param item Item of generic type to add to the managed items collection
     */
    void addItem(T item);

    /**
     * Return a List with all items
     * @return List of type T with all items
     */
    List<T> listAllItems();

    /**
     * Return one random item out of the collection
     * @return item of generic type T
     * @param priceDescription String with a description of the price
     */
    T giveRandomItem(String priceDescription);

    /**
     * Returns a list with all the winners
     * @return List with all the winners
     */
    List<T> listAllWinners();
}
