package nl.gridshore.samples.raffle.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 2-nov-2007
 * Time: 21:23:49
 * Entity class representing a Raffle type
 */
@Entity
@Table(name = "raffles")
public class Raffle extends BaseDomain {
    private String title;
    private String description;
    // Due to a limitation of JPA we cannot eager fetch multiple bags, so we need a diferent mechanism. Check the Dao
    // for a raffle.
    @OneToMany(mappedBy = "raffle", fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.REMOVE})
    private List<Prize> prizes = new ArrayList<Prize>();
    @OneToMany(mappedBy = "raffle", fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.REMOVE})
    private List<Participant> participants = new ArrayList<Participant>();

    public Raffle() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addPrize(final Prize prize) {
        prize.setRaffle(this);
        this.prizes.add(prize);
    }

    public void removePrize(final Prize prize) {
        this.prizes.remove(prize);
    }

    public List<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<Prize> prizes) {
        this.prizes = prizes;
    }

    public void addParticipant(final Participant participant) {
        participant.setRaffle(this);
        this.participants.add(participant);
    }

    public void removeParticipant(final Participant participant) {
        this.participants.remove(participant);
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

}
