package nl.gridshore.newsfeed.domain;

import javax.persistence.*;

/**
 * Root entity for a Message received by the application through mail or Xmpp
 *
 * @author Jettro Coenradie
 */
@Entity
public class ReceivedMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String content;

    @Basic
    private String sender;

    public ReceivedMessage() {
    }

    public ReceivedMessage(String content, String sender) {
        this();
        this.content = content;
        this.sender = sender;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }
}
