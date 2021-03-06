package nl.gridshore.samples.raffle.domain;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 2-nov-2007
 * Time: 21:45:56
 * Base entity class for the Prize
 */
@Entity
@Table(name = "prizes")
public class Prize extends BaseDomain {
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "raffle_id")
    private Raffle raffle;
    @OneToOne(mappedBy = "prize", cascade = {CascadeType.ALL, CascadeType.REMOVE})
    private Winner winner;

    public Prize() {
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

    public Winner getWinner() {
        return winner;
    }

    public void setWinner(Winner winner) {
        this.winner = winner;
    }
}
