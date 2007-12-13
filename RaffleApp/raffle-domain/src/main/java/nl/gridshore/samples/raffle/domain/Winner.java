package nl.gridshore.samples.raffle.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 2-nov-2007
 * Time: 21:25:31
 * Entity class representing a Winner
 */
@Entity
@Table(name = "winners")
public class Winner extends BaseDomain {
    @OneToOne
    @JoinColumn(name = "price_id")
    private Price price;
    @OneToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    public Winner() {
    }

    public Winner(Price price, Participant participant) {
        this.price = price;
        this.participant = participant;
    }

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