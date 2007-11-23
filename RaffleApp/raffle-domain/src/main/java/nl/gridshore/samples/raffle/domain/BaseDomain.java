package nl.gridshore.samples.raffle.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 2-nov-2007
 * Time: 21:18:21
 * This is the super class for all domain classes
 */
@MappedSuperclass
public class BaseDomain implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
