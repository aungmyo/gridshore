package nl.gridshore.samples.raffle.server;

import nl.gridshore.samples.raffle.server.TextFile;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 10-okt-2007
 * Time: 20:45:59
 * Helper class to load and store items in a file.
 */
public class RaffleFileInteraction {
    private String filename;
    private TextFile items;

    public RaffleFileInteraction(String filename) {
        this.filename = filename;
        items =  new TextFile(filename);
    }

    public synchronized void storeItem(String item) {
        items.add(item);
        items.write(filename);
    }

    public synchronized List<String> loadItems() {
        return items;
    }
}
