package nl.gridshore.newsfeed.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Jettro Coenradie
 */
@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "commenter")
    private String commenter;
    @Column(name = "content")
    private String content;
    @Column(name = "dateCreated")
    private Date dateCreated;

    public Comment() {
        dateCreated = new Date();
    }

    public Comment(String commenter, String content) {
        this();
        this.commenter = commenter;
        this.content = content;
    }

    public String commenter() {
        return this.commenter;
    }

    public Date dateCreated() {
        return this.dateCreated;
    }

    public String content() {
        return this.content;
    }

    /* Getters for taglibs */
    public Date getDateCreated() {
        return dateCreated;
    }

    public String getCommenter() {
        return commenter;
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }
}
