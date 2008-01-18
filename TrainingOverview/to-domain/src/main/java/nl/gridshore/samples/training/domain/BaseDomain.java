package nl.gridshore.samples.training.domain;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Jan 19, 2008
 * Time: 12:05:41 AM
 * This is the super class for all domain classes
 */
@MappedSuperclass
public class BaseDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNew() {
        return (id == null);
    }
}
