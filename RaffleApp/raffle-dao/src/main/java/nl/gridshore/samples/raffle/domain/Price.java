package nl.gridshore.samples.raffle.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 2-nov-2007
 * Time: 21:45:56
 * Base entity class for the Price
 */
@Entity
@Table(name = "prices")
public class Price extends BaseDomain {
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "raffle_id")
    private Raffle raffle;
    @OneToOne
    private Winner winner;

    public Price() {
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

    public Raffle getRaffle() {
        return raffle;
    }

    public void setRaffle(Raffle raffle) {
        this.raffle = raffle;
    }

    @ManyToOne(optional = false)
    private Raffle raffles;

    public Raffle getRaffles() {
        return raffles;
    }

    public void setRaffles(Raffle raffles) {
        this.raffles = raffles;
    }
}