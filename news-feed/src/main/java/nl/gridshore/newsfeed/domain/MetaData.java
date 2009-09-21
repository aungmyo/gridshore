package nl.gridshore.newsfeed.domain;

import org.springframework.util.StringUtils;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Contains metadata properties of the content item
 *
 * @author Jettro Coenradie
 */
@Embeddable
public class MetaData {
    @Column(name = "author")
    private String author;
    @Column(name = "publication_date")
    private Date publicationDate;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "tags")
    private String tags;

    public MetaData() {
    }

    public MetaData(String author, Date publicationDate) {
        this.author = author;
        this.publicationDate = publicationDate;
        this.creationDate = new Date();
    }

    /**
     * Returns the name of the author of the item this meta data belangs to
     *
     * @return String cotaining the name of the author of this item
     */
    public String author() {
        return author;
    }

    /**
     * Retrieve the publication date
     *
     * @return Date representing the date when the item this meta data belongs to is published
     */
    public Date publicationDate() {
        return publicationDate;
    }

    /**
     * Returns the date that this metadata item was created
     *
     * @return The date this metadata was created
     */
    public Date creationDate() {
        return creationDate;
    }

    /**
     * Returns a string array containing all the tags
     *
     * @return String[] containing the tags
     */
    public String[] tags() {
        if (tags == null) {
            return new String[]{};
        }
        return StringUtils.commaDelimitedListToStringArray(tags);
    }

    /**
     * Should only be called by the item it belongs to. The tag is only added if it is not null and not an empty string.
     * The new tag cannot contain a ",", an exception will be thrown if it does.
     *
     * @param newTag String containing the tag to add.
     */
    void tag(String newTag) {
        Assert.doesNotContain(newTag,",");
        if (StringUtils.hasText(newTag)) {
            if (StringUtils.hasText(tags)) {
                tags += ",";
            } else {
                tags = "";
            }
            tags += newTag;
        }
    }
}
