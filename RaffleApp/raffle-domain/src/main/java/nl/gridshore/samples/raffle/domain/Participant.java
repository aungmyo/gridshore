package nl.gridshore.samples.raffle.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 2-nov-2007
 * Time: 21:25:41
 * Entity class representing a Participant
 */
@Entity
@Table (name = "participants")
public class Participant extends BaseDomain {
    private String name;
    @ManyToOne
    @JoinColumn(name = "raffle_id")
    private Raffle raffle;

    public Participant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Raffle getRaffle() {
        return raffle;
    }

    public void setRaffle(Raffle raffle) {
        this.raffle = raffle;
    }
}
