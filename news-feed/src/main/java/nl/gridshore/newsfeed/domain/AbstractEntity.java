package nl.gridshore.newsfeed.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.ConcurrentModificationException;

/**
 * @author Jettro Coenradie
 */
@MappedSuperclass
public class AbstractEntity {
    @Id
    @GeneratedValue
    private Long id;

    @SuppressWarnings({"UnusedDeclaration"})
    @Version
    private long version;

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public void assertVersion(long version) {
        if (version != this.version) {
            throw new ConcurrentModificationException("The update is performed using stale data");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;
        if (that.id == null && this.id == null && this != that) return false;
        //noinspection RedundantIfStatement
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
