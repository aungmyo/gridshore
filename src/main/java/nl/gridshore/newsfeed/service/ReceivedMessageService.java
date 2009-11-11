package nl.gridshore.newsfeed.service;

import nl.gridshore.newsfeed.domain.ReceivedMessage;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service to handle everything related to {@code ReceivedMessage} objects.
 *
 * @author Jettro Coenradie
 */
public interface ReceivedMessageService {
    /**
     * Create a new ReceivedMessage
     *
     * @param sender String containing information about the sender of the received message
     * @param content String containing the content of the message.
     */
    @Transactional
    void createReceivedMessage(String sender, String content);

    /**
     * Returns a list with all available received messages
     * @return List of received messages
     */
    @Transactional(readOnly = true)
    List<ReceivedMessage> listAllReceivedMessages();
}
