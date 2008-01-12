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
    @JoinColumn(name = "prize_id")
    private Prize prize;
    @OneToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    public Winner() {
    }

    public Winner(Prize prize, Participant participant) {
        this.prize = prize;
        this.participant = participant;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}