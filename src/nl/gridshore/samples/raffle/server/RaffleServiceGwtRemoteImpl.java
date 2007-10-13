package nl.gridshore.samples.raffle.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import nl.gridshore.samples.raffle.client.RaffleServiceGwtRemote;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 10-okt-2007
 * Time: 23:05:09
 * To change this template use File | Settings | File Templates.
 */
public class RaffleServiceGwtRemoteImpl extends RemoteServiceServlet implements RaffleServiceGwtRemote {
    private transient RaffleService<String> raffleService;

    public RaffleServiceGwtRemoteImpl() {
        raffleService = new RaffleServiceStringFileBased("d:/personnames.txt","d:/winners.txt");
    }

    public String getRandomName(String priceDesc) {
        return raffleService.giveRandomItem(priceDesc);
    }

    public ArrayList getAllNames() {
        return new ArrayList(raffleService.listAllItems());
    }

    public void storeName(String name) {
        raffleService.addItem(name);
    }

    public ArrayList getAllWinners() {
        return new ArrayList(raffleService.listAllWinners());
    }
}