package nl.gridshore.samples.books.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: jettro
 * Date: Mar 18, 2008
 * Time: 1:20:49 PM
 * Base domain class for all domain classes
 */
@MappedSuperclass
public class BaseDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public BaseDomain() {
    }

    public BaseDomain(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @SuppressWarnings({"JpaModelErrorInspection"})
    public boolean isNew() {
        return (id == null);
    }
}
