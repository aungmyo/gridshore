package nl.gridshore.newsfeed.domain;

import java.util.List;

/**
 * All datastore related actions on {@code ReceivedMessage}
 *
 * @author Jettro Coenradie
 */
public interface ReceivedMessageRepository {
    /**
     * Store a received message in the repository
     *
     * @param receivedMessage ReceivedMessage to be stored
     */
    void persist(ReceivedMessage receivedMessage);

    /**
     * Returns all available received messages
     * @return List containing all received messages
     */
    List<ReceivedMessage> listAllReceivedMessages();
}
