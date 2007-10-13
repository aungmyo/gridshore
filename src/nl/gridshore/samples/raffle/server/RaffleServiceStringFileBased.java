package nl.gridshore.samples.raffle.server;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 10-okt-2007
 * Time: 22:39:45
 * Implementation of the RaffleService using a String and a file for storage
 */
public class RaffleServiceStringFileBased implements RaffleService<String> {
    private RaffleFileInteraction storage;
    private RaffleFileInteraction winnersStorage;
    private Set<String> winners = new HashSet<String>();

    public RaffleServiceStringFileBased(String fileNames, String fileWinners) {
        storage = new RaffleFileInteraction(fileNames);
        winnersStorage = new RaffleFileInteraction(fileWinners);
    }

    public void addItem(String item) {
        storage.storeItem(item);
    }

    public List<String> listAllItems() {
        return storage.loadItems();
    }

    public String giveRandomItem(String priceDescription) {
        String randomName = findRandomItem();
        while (winners.contains(randomName)) {
            randomName = findRandomItem();
        }
        winners.add(randomName);
        String message = randomName + " won " + priceDescription;
        winnersStorage.storeItem(message);
        return message;
    }

    public List<String> listAllWinners() {
        return winnersStorage.loadItems();
    }

    private String findRandomItem() {
        List<String> items = storage.loadItems();
        Random random = new Random(System.currentTimeMillis());
        int randomInt = random.nextInt(items.size());
        return items.get(randomInt);
    }
}
