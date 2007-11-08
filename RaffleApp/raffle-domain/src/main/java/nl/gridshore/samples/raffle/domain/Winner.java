package nl.gridshore.samples.raffle.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 2-nov-2007
 * Time: 21:25:31
 * Entity class representing a Winner
 */
@Entity
@Table (name = "winners")
public class Winner extends BaseDomain {
    @OneToOne
    @JoinColumn(name = "price_id")
    private Price price;
    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}